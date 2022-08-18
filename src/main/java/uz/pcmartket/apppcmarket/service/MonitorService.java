package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Monitor;
import uz.pcmartket.apppcmarket.enums.Matritsa;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MonitorDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.MonitorRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MonitorService {
    @Autowired
    private MonitorRepository monitorRepository;
    @Autowired
    private BrandRepository brandRepository;


    public ApiResponse getMonitor(Integer id) {
        Optional<Monitor> optionalMonitor = monitorRepository.findById(id);
        return optionalMonitor.map(monitor -> new ApiResponse("Monitor found", true, monitor)).orElse(
                new ApiResponse("Monitor not found", false));
    }

    public List<Monitor> getMonitors() {
        return monitorRepository.findAll();
    }


    public ApiResponse addMonitor(MonitorDto monitorDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(monitorDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Matritsa matritsa = containsMatritsa(monitorDto.getMatritsaId());
        if (Objects.isNull(matritsa))
            return new ApiResponse("Such a screen matrix does not exist", false);

        Monitor monitor = new Monitor();
        monitor.setMatritsa(matritsa);
        monitor.setCurvedScreen(monitorDto.getCurvedScreen());
        monitor.setResponseTime(monitorDto.getResponseTime());
        monitor.setScreenDiagonal(monitorDto.getScreenDiagonal());
        monitor.setScreenResolution(monitorDto.getScreenResolution());
        monitor.setScreenRefreshRate(monitorDto.getScreenRefreshRate());
        monitor.setVideoConnectors(monitorDto.getVideoConnectors());
        monitor.setBrand(optionalBrand.get());
        monitor.setName(monitorDto.getName());
        monitor = monitorRepository.save(monitor);

        return new ApiResponse("Monitor seccessfully saved", true, monitor);
    }

    public ApiResponse editMonitor(Integer id, MonitorDto monitorDto) {
        Optional<Monitor> optionalMonitor = monitorRepository.findById(id);
        if (!optionalMonitor.isPresent()) return new ApiResponse("Monitor not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(monitorDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Matritsa matritsa = containsMatritsa(monitorDto.getMatritsaId());
        if (Objects.isNull(matritsa))
            return new ApiResponse("Such a screen matrix does not exist", false);

        Monitor monitor = optionalMonitor.get();
        monitor.setMatritsa(matritsa);
        monitor.setCurvedScreen(monitorDto.getCurvedScreen());
        monitor.setResponseTime(monitorDto.getResponseTime());
        monitor.setScreenDiagonal(monitorDto.getScreenDiagonal());
        monitor.setScreenResolution(monitorDto.getScreenResolution());
        monitor.setScreenRefreshRate(monitorDto.getScreenRefreshRate());
        monitor.setVideoConnectors(monitorDto.getVideoConnectors());
        monitor.setBrand(optionalBrand.get());
        monitor.setName(monitorDto.getName());
        monitor = monitorRepository.save(monitor);

        return new ApiResponse("Monitor seccessfully edited", true, monitor);
    }

    public ApiResponse deleteMonitor(Integer id) {
        Optional<Monitor> optionalMonitor = monitorRepository.findById(id);
        if (!optionalMonitor.isPresent()) return new ApiResponse("Monitor not found", false);

        return new ApiResponse("Monitor seccessfully deleted", true);
    }

    private Matritsa containsMatritsa(Integer ordinal) {
        for (Matritsa value : Matritsa.values()) {
            if (value.ordinal() == ordinal) return value;
        }
        return null;
    }
}
