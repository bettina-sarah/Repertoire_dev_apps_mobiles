package com.example.annexe_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

public class Memo implements Serializable {

    private LocalDate dateEcheance;

    private String memo;

    public Memo(String memo, LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        this.memo = memo;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
