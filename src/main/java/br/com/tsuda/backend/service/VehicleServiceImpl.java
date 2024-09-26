package br.com.tsuda.backend.service;

import br.com.tsuda.backend.domain.converter.VehicleConverter;
import br.com.tsuda.backend.domain.dto.request.VehicleRequestDto;
import br.com.tsuda.backend.domain.dto.response.VehicleResponseDto;
import br.com.tsuda.backend.domain.entity.Vehicle;
import br.com.tsuda.backend.domain.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleResponseDto create(VehicleRequestDto request) {
        Vehicle vehicle = VehicleConverter.toVehicleEntity(request);

        return VehicleConverter.toVehicleResponseDto(vehicleRepository.save(vehicle));
    }

    @Override
    public List<VehicleResponseDto> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles.stream().map(VehicleConverter::toVehicleResponseDto).toList();
    }

    @Override
    public VehicleResponseDto getById(int id) {
        Vehicle vehicle = vehicleRepository.findById(id).get();

        return VehicleConverter.toVehicleResponseDto(vehicle);
    }

    @Override
    public VehicleResponseDto update(int id, VehicleRequestDto request) {
        Vehicle vehicle = vehicleRepository.findById(id).get();

        Vehicle updatedVehicle = vehicleRepository.save(VehicleConverter.toUpdatedVehicle(vehicle, request));

        return VehicleConverter.toVehicleResponseDto(updatedVehicle);
    }
}