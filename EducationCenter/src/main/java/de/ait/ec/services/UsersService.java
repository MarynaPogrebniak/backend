package de.ait.ec.services;

import de.ait.ec.dto.UserDto;

public interface UsersService {
    UserDto getUser(Long userId);
}
