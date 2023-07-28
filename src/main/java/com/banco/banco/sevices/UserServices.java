package com.banco.banco.sevices;

import com.banco.banco.dto.MessageMockDTO;
import com.banco.banco.dto.PayLoadDTO;
import com.banco.banco.exceptionhandler.exceptions.InsufficientBalanceException;
import com.banco.banco.exceptionhandler.exceptions.NullUserExceptions;
import com.banco.banco.exceptionhandler.exceptions.ShopkeeperException;
import com.banco.banco.models.Users;
import com.banco.banco.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UsersRepository usersRepository;

    @Autowired
    public UserServices(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public void addNewUser(Users user){
        usersRepository.save(user);
    }

    @Transactional
    public void transferService(PayLoadDTO payLoadDTO){
        Optional<Users> payerOptional = usersRepository.findById(payLoadDTO.payer());
        Optional<Users> payeeOptional = usersRepository.findById(payLoadDTO.payee());

        if (payeeOptional.isEmpty()){
            throw new NullUserExceptions(payLoadDTO.payee());
        } else if (payerOptional.isEmpty()){
            throw new NullUserExceptions(payLoadDTO.payer());
        }

        Users payer = payerOptional.get();
        Users payee = payeeOptional.get();

        if (!payer.isShopkeeper()){
            throw new ShopkeeperException(payer.getId());
        }

        long amountToTransfer = payLoadDTO.value();
        long payerBalance = payer.getBalance();
        long payeeBalance = payee.getBalance();

        if (payerBalance < amountToTransfer){
            throw new InsufficientBalanceException(payerBalance, amountToTransfer, payer.getId());
        }

        payer.setBalance(payerBalance - amountToTransfer);
        payee.setBalance(payeeBalance + amountToTransfer);

        verifyExternalService();

    }

    public void verifyExternalService(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
        ResponseEntity<MessageMockDTO> mockResponse = restTemplate.getForEntity(url, MessageMockDTO.class);

        if (mockResponse.getStatusCode().is5xxServerError()){
            throw new RuntimeException("server error");
        }
    }

}

