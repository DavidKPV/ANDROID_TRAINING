package com.davidkpv.kpvgit.db;
import android.annotation.SuppressLint;

import androidx.room.TypeConverter;
import androidx.room.util.StringUtil;

import java.util.Collections;
import java.util.List;

@SuppressLint("RestrictedApi")
public class GitHubTypeConverters {
    // SE UTILIZA ESTA ETIQUETA PARA NOTIFICAR LA CONVERSIÓN DE DATOS QUE NO SE PUEDEN GUARDAR
    // LA ETIQUETA OBLIGA A QUE SE GUARDEN
    // LA SIGUIENTE CLASE CONVERTIRÁ DE STRINGS A LISTA DE ENTEROS
    @TypeConverter
    public static List<Integer> stringToIntList(String data){
        if(data == null){
            return Collections.emptyList();
        }
        // TRANSFORMAMOS CADA STRING QUE PASE A ENTERO
        return StringUtil.splitToIntList(data);
    }

    @TypeConverter
    public static  String intListtoString(List<Integer> ints){
        return StringUtil.joinIntoString(ints);
    }
}
