package de.ait.ec.services;

import de.ait.ec.dto.RegisterDto;
import de.ait.ec.dto.UserDto;

public interface RegistrationService {
    UserDto register(RegisterDto registerData);
}
