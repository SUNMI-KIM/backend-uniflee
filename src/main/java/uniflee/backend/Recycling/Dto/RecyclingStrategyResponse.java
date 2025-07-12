package uniflee.backend.Recycling.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "재활용 방법 DTO")
public class RecyclingStrategyResponse {
    @Schema(description = "재활용품 포인트", example = "5")
    private int point;
    @Schema(description = "재활용품 이름", example = "PE")
    private String displayName;
    @Schema(description = "탄소 절감량", example = "0.3")
    private double co2;
    @Schema(description = "재활용품 배출법 1", example = "이렇게 하세요")
    private String disposalInstructions1;  // 분리 배출법
    @Schema(description = "재활용품 배출법 2", example = "저렇게 하세요")
    private String disposalInstructions2;  // 분리 배출법
    @Schema(description = "재활용품 배출법 3", example = "요렇게 하세요")
    private String disposalInstructions3;  // 분리 배출법
}