package mvc.promiseme.meeting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.common.exception.ErrorResponse;
import mvc.promiseme.meeting.dto.MeetingResponseDTO;
import mvc.promiseme.meeting.dto.SummaryRequestDTO;
import mvc.promiseme.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@Tag(name = "Meeting", description = "회의 API")
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;
    @Operation(summary = "회의 조회", description = "해당 프로젝트의 회의를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = MeetingResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))


    })
    @GetMapping("/")
    public ResponseEntity<List<MeetingResponseDTO>> meetingAll(@RequestParam(name="projectId") Long projectId){
        return ResponseEntity.ok(meetingService.meetingAll(projectId));
    }

    @Operation(summary = "음성 텍스트 변환", description = "음성을 텍스트로 변환한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping("/transfer")
    public ResponseEntity<String> transferVoice(@RequestParam("voiceFile") MultipartFile multipartFile, @RequestParam("projectId") Long projectId){
        return ResponseEntity.ok(meetingService.voiceToMeeting(multipartFile, projectId));
    }
    @Operation(summary = "회의 요약", description = "회의 내용을 clova studio를 이용하여 요약한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "해당 프로젝트를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "해당 회의를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "501", description = "알수 없는 에러로 투두를 생성할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))


    })
    @PostMapping("/summary")
    public ResponseEntity<String> summaryText(@RequestBody SummaryRequestDTO summaryRequestDTO) {
        return ResponseEntity.ok(meetingService.textToMeeting(summaryRequestDTO.getMeetingContent(), summaryRequestDTO.getProjectId()));
    }
}
