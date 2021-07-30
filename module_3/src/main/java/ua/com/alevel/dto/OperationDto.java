package ua.com.alevel.dto;

import java.time.Instant;

public class OperationDto {
    private long id;
    private long amount;
    private String description;
    private Instant timestamp;
    private long balanceId;

    public long getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public long getBalanceId() {
        return balanceId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBalanceId(long balanceId) {
        this.balanceId = balanceId;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
