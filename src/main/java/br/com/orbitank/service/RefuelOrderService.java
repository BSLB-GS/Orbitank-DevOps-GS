package br.com.orbitank.service;

import br.com.orbitank.dto.Request.RefuelOrderRequest;
import br.com.orbitank.dto.Response.RefuelOrderResponse;
import br.com.orbitank.entity.RefuelOrder;
import br.com.orbitank.entity.SupplyRequest;
import br.com.orbitank.enums.AlertSeverity;
import br.com.orbitank.enums.SupplyRequestStatus;
import br.com.orbitank.repository.OperationalAlertRepository;
import br.com.orbitank.repository.RefuelOrderRepository;
import br.com.orbitank.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefuelOrderService {

    private final RefuelOrderRepository repository;
    private final SupplyRequestRepository supplyRequestRepository;
    private final OperationalAlertRepository alertRepository;

    public List<RefuelOrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RefuelOrderResponse findById(Long id) {
        RefuelOrder entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem não encontrada"));

        return toResponse(entity);
    }

    public RefuelOrderResponse create(RefuelOrderRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public RefuelOrderResponse createFromSupplyRequest(Long supplyRequestId, Long stationId, RefuelOrderRequest request) {

        SupplyRequest supplyRequest = supplyRequestRepository.findById(supplyRequestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação de abastecimento não encontrada."));

        if (supplyRequest.getStatus() != SupplyRequestStatus.APPROVED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bloqueado: A solicitação de abastecimento não está aprovada.");
        }

        long criticalAlerts = alertRepository.countByLunarStationIdAndSeverityAndActiveTrue(stationId, AlertSeverity.CRITICAL);
        if (criticalAlerts > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operação bloqueada: Existem alertas críticos ativos na estação.");
        }

        RefuelOrder order = toEntity(request);
        order.setSupplyRequest(supplyRequest);

        return toResponse(repository.save(order));
    }

    public RefuelOrderResponse update(Long id, RefuelOrderRequest request) {
        RefuelOrder entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem não encontrada"));

        SupplyRequest supplyRequest = supplyRequestRepository.findById(request.getSupplyRequestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada"));

        entity.setSupplyRequest(supplyRequest);
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setActualWaterTransferred(request.getActualWaterTransferred());
        entity.setActualH2Transferred(request.getActualH2Transferred());
        entity.setActualO2Transferred(request.getActualO2Transferred());
        entity.setStatus(request.getStatus());
        entity.setTankStatus(request.getTankStatus());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private RefuelOrderResponse toResponse(RefuelOrder entity) {
        return RefuelOrderResponse.builder()
                .id(entity.getId())
                .supplyRequestId(entity.getSupplyRequest().getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .actualWaterTransferred(entity.getActualWaterTransferred())
                .actualH2Transferred(entity.getActualH2Transferred())
                .actualO2Transferred(entity.getActualO2Transferred())
                .status(entity.getStatus())
                .tankStatus(entity.getTankStatus())
                .build();
    }

    private RefuelOrder toEntity(RefuelOrderRequest request) {
        SupplyRequest supplyRequest = supplyRequestRepository.findById(request.getSupplyRequestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada"));

        return RefuelOrder.builder()
                .supplyRequest(supplyRequest)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .actualWaterTransferred(request.getActualWaterTransferred())
                .actualH2Transferred(request.getActualH2Transferred())
                .actualO2Transferred(request.getActualO2Transferred())
                .status(request.getStatus())
                .tankStatus(request.getTankStatus())
                .build();
    }
}