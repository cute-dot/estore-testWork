package ru.isands.test.estore.dao.mapper;

import org.springframework.stereotype.Component;
import ru.isands.test.estore.dao.dto.ElectronicDto;
import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.ElectronicsType;

@Component
public class ElectronicMapper {

    public ElectronicDto toDto(Electronic electronic) {
        if (electronic == null) {
            return null;
        }

        return new ElectronicDto(
                electronic.getId(),
                electronic.getName(),
                electronic.getType() != null ? electronic.getType().getId() : null,
                electronic.getPrice(),
                electronic.getQuantity(),
                electronic.getArchived(),
                electronic.getDescription()
        );
    }

    public Electronic toEntity(ElectronicDto electronicDto) {
        if (electronicDto == null) {
            return null;
        }

        Electronic electronic = new Electronic();
        electronic.setId(electronicDto.getId());
        electronic.setName(electronicDto.getName());
        electronic.setPrice(electronicDto.getPrice());
        electronic.setQuantity(electronicDto.getQuantity());
        electronic.setArchived(electronicDto.getArchived());
        electronic.setDescription(electronicDto.getDescription());

        return electronic;
    }
}

