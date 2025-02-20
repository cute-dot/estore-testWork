package ru.isands.test.estore.dao.service;

import ru.isands.test.estore.dao.entity.Electronic;
import ru.isands.test.estore.dao.entity.ElectronicStore;

import java.util.List;

public interface ElectronicsService {

    List<Electronic> GetAllElectronic();
}
