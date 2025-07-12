package uniflee.backend.Recycling.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uniflee.backend.Recycling.Dto.RecyclingRequestDto;
import uniflee.backend.Recycling.Dto.RecyclingResponseDto;
import uniflee.backend.Recycling.Dto.RecyclingStrategyResponse;
import uniflee.backend.Recycling.Service.RecyclingService;
import uniflee.backend.Recycling.domain.ItemType;

import java.util.List;

@RestController
@RequestMapping("/api/recycling")
@RequiredArgsConstructor
@Tag(name = "RecyclingController", description = "재활용 관련 API")
public class RecyclingController {

    private final RecyclingService recyclingService;

    @Operation(
            summary = "분리배출 목록을 추가합니다.",
            description = "유저의 분리 배출 목록을 추가합니다."
    )
    @PostMapping
    public ResponseEntity<String> addRecycling(Authentication authentication,
                                               @RequestBody RecyclingRequestDto recyclingDto) {
        recyclingService.addRecycling(authentication.getName(), recyclingDto.getItemType(), recyclingDto.getCount());
        return ResponseEntity.ok("포인트 적립");
    }

    @Operation(
            summary = "분리 배출 내역을 가져옵니다.",
            description = "유저의 분리 배출 내역을 가져옵니다."
    )
    @GetMapping
    public ResponseEntity<List<RecyclingResponseDto>> getRecycling(Authentication authentication) {
        List<RecyclingResponseDto> responseDtoList = recyclingService.getRecycling(authentication.getName());
        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(
            summary = "분리 배출 가이드를 가져옵니다.",
            description = "분리 배출 품목에 따른 배출 가이드를 가져옵니다."
    )
    @GetMapping("/guide")
    public ResponseEntity<RecyclingStrategyResponse> getRecyclingGuide(@RequestParam String itemType) {
        ItemType type = ItemType.valueOf(itemType);
        RecyclingStrategyResponse response = RecyclingStrategyResponse.builder()
                .point(type.getPoints())
                .co2(type.getCo2())
                .displayName(type.getDisplayName())
                .disposalInstructions1(type.getDisposalInstructions1())
                .disposalInstructions2(type.getDisposalInstructions2())
                .disposalInstructions3(type.getDisposalInstructions3())
                .build();
        return ResponseEntity.ok(response);
    }
}
