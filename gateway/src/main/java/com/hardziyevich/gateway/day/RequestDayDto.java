package com.hardziyevich.gateway.day;

import javax.validation.constraints.NotNull;

public record RequestDayDto(@NotNull String groomer, @NotNull String serviceType) {
}
