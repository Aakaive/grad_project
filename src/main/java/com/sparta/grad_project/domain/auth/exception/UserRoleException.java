package com.sparta.grad_project.domain.auth.exception;


import com.sparta.grad_project.global.exception.GlobalException;

import static com.sparta.grad_project.global.exception.GlobalExceptionConst.UNAUTHORIZED_OWNERTOKEN;


public class UserRoleException extends GlobalException {
    public UserRoleException () {
        super(UNAUTHORIZED_OWNERTOKEN);
    }
}
