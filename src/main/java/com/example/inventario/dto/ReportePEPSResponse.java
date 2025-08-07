package com.example.inventario.dto;

import java.util.List;

public class ReportePEPSResponse {
    private List<LotePEPS> lotes;
    private ResumenPEPS resumen;

    public ReportePEPSResponse(List<LotePEPS> lotes, ResumenPEPS resumen) {
        this.lotes = lotes;
        this.resumen = resumen;
    }

    public List<LotePEPS> getLotes() {
        return lotes;
    }

    public void setLotes(List<LotePEPS> lotes) {
        this.lotes = lotes;
    }

    public ResumenPEPS getResumen() {
        return resumen;
    }

    public void setResumen(ResumenPEPS resumen) {
        this.resumen = resumen;
    }
}