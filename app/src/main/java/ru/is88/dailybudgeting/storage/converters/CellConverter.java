package ru.is88.dailybudgeting.storage.converters;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.converter.TypeConverter;

import ru.is88.dailybudgeting.domain.models.Cell;

@com.raizlabs.android.dbflow.annotation.TypeConverter
public class CellConverter extends TypeConverter<String, Cell> {

    @Override
    public String getDBValue(@NonNull Cell model) {
        return model.getValue();
    }

    @Override
    public Cell getModelValue(String data) {
        return new Cell(data);
    }
}
