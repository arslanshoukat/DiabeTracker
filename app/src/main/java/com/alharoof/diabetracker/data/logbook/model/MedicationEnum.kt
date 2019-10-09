package com.alharoof.diabetracker.data.logbook.model

enum class MedicationEnum(val code: Int, val productName: String, val type: Int = Medication.OTHER) {
    AFREZZA(0, "Afrezza", Medication.RAPID_ACTING_INSULIN),
    APIDRA(1, "Apidra", Medication.RAPID_ACTING_INSULIN),
    BASAGLAR(2, "Basaglar", Medication.LONG_ACTING_INSULIN),
    HUMALOG(3, "Humalog", Medication.RAPID_ACTING_INSULIN),
    HUMALOG_MIX_50_50(4, "Humalog Mix 50/50", Medication.RAPID_ACTING_INSULIN),
    HUMALOG_MIX_75_25(5, "Humalog Mix 75/25", Medication.RAPID_ACTING_INSULIN),
    HUMULIN_R(6, "Humulin R", Medication.SHORT_ACTING_INSULIN),
    HUMULIN_N(7, "Humulin N", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMULIN_50_50(8, "Humulin 50/50", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMULIN_70_30(9, "Humulin 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    LANTUS(10, "Lantus", Medication.LONG_ACTING_INSULIN),
    LEVEMIR(11, "Levemir", Medication.LONG_ACTING_INSULIN),
    NOVOLIN_R(12, "Novolin R", Medication.SHORT_ACTING_INSULIN),
    NOVOLIN_N(12, "Novolin N", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVOLIN_70_30(13, "Novolin 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVOLOG(14, "NovoLog", Medication.RAPID_ACTING_INSULIN),
    NOVOLOG_MIX_70_30(15, "NovoLog Mix 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVORAPID(16, "NovoRapid", Medication.RAPID_ACTING_INSULIN),
    NPH(17, "NPH", Medication.INTERMEDIATE_ACTING_INSULIN),
    TOUJEO(18, "Toujeo", Medication.LONG_ACTING_INSULIN),
    TRESIBA(19, "Tresiba", Medication.LONG_ACTING_INSULIN),
    VELOSULIN(20, "Velosulin", Medication.SHORT_ACTING_INSULIN),
    METFORMIN(101, "Metformin", Medication.DRUGS)
}