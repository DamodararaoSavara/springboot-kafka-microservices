package net.javaguides.springboot.Mapper;

import net.javaguides.dto.OrderEventDto;
import net.javaguides.springboot.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "id",target = "orderId")
    OrderEventDto toDto(Order order);
}
