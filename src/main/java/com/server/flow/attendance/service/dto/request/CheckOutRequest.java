package com.server.flow.attendance.service.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record CheckOutRequest(
	@NotNull(message = "출퇴근 기록 ID 정보가 필요합니다.")
	Long attendanceId,
	@NotNull(message = "출퇴근 날짜 정보가 필요합니다.")
	LocalDate attendanceDate,
	@NotNull(message = "퇴근 시간 정보가 필요합니다.")
	LocalTime checkOutTime
) {
}