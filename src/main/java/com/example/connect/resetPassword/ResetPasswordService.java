package com.example.connect.resetPassword;

import com.example.connect.exceptions.ExpiredToken;
import com.example.connect.exceptions.InvalidToken;
import com.example.connect.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class ResetPasswordService {

    @Autowired
    private ResetPasswordRepo resetPasswordRepo;

    public ResetPassword create (User user, int token) {

        Optional<ResetPassword> optionalPasswordResetToken = resetPasswordRepo.findByUser_Id(user.getId());

        ResetPassword resetPassword = new ResetPassword();

        if (optionalPasswordResetToken.isPresent()) resetPassword = optionalPasswordResetToken.get();

        resetPassword.setToken(token);
        resetPassword.setUser(user);

        return resetPasswordRepo.save(resetPassword);
    }

    public ResetPassword findOneByToken (int token) throws RuntimeException {
        Optional<ResetPassword> resetPassword = resetPasswordRepo.findByToken(token);

        if (resetPassword.isEmpty()) throw new InvalidToken();

        return resetPassword.get();
    }

//    public boolean verifyUserToken () throws RuntimeException {
//
//    }

    private void checkTokenExpiry (Date lastModifiedAt) throws RuntimeException {

        Date now = new Date(System.currentTimeMillis());

        if (!now.before(new Date(lastModifiedAt.getTime() + (1000 * 300))))
            throw new ExpiredToken("Password reset link's expired. Request a new password reset link");
    }
}
