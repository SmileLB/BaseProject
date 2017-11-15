package com.example.uilibrary.dialog;

import java.io.Serializable;

public interface ViewConvertListener extends Serializable {
    void convertView(ViewHolder holder, BaseSuperDialog dialog);
}
