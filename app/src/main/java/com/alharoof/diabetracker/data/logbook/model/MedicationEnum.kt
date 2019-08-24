package com.alharoof.diabetracker.data.logbook.model

enum class MedicationEnum(val code: Int, val productName: String, val type: Int = Medication.OTHER) {
    AFREZZA(1, "Afrezza"),
    APIDRA(2, "Apidra"),
    BASAGLAR(3, "Basaglar"),
    HUMALOG(4, "Humalog"),
    HUMALOG_MIX_50_50(5, "Humalog Mix 50/50"),
    HUMALOG_MIX_75_25(6, "Humalog Mix 75/25"),
    HUMULIN_R(7, "Humulin R"),
    HUMULIN_N(8, "Humulin N"),
    HUMULIN_50_50(9, "Humulin 50/50"),
    HUMULIN_70_30(10, "Humulin 70/30"),
    LANTUS(11, "Lantus"),
    LEVEMIR(12, "Levemir"),
    NOVOLIN(13, "Novolin"),
    NOVOLIN_70_30(14, "Novolin 70/30"),
    NOVOLOG(15, "NovoLog"),
    NOVOLOG_MIX_70_30(16, "NovoLog Mix 70/30"),
    NOVORAPID(17, "NovoRapid"),
    NPH(18, "NPH"),
    TOUJEO(19, "Toujeo"),
    TRESIBA(20, "Tresiba"),
    VELOSULIN(21, "Velosulin")
}