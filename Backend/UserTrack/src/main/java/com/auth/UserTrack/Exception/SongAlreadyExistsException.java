package com.auth.UserTrack.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "SongAlreadyExists")
public class SongAlreadyExistsException extends Exception{
}
