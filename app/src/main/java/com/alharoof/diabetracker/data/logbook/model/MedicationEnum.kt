package com.alharoof.diabetracker.data.logbook.model

enum class MedicationEnum(val code: Int, val productName: String, val type: Int = Medication.OTHER) {

    //  todo: fix type of insulins
    AFREZZA(1, "Afrezza", Medication.LONG_ACTING_INSULIN),
    APIDRA(2, "Apidra", Medication.RAPID_ACTING_INSULIN),
    BASAGLAR(3, "Basaglar", Medication.LONG_ACTING_INSULIN),
    HUMALOG(4, "Humalog", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMALOG_MIX_50_50(5, "Humalog Mix 50/50", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMALOG_MIX_75_25(6, "Humalog Mix 75/25", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMULIN_R(7, "Humulin R", Medication.SHORT_ACTING_INSULIN),
    HUMULIN_N(8, "Humulin N", Medication.LONG_ACTING_INSULIN),
    HUMULIN_50_50(9, "Humulin 50/50", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMULIN_70_30(10, "Humulin 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    LANTUS(11, "Lantus", Medication.LONG_ACTING_INSULIN),
    LEVEMIR(12, "Levemir", Medication.LONG_ACTING_INSULIN),
    NOVOLIN(13, "Novolin", Medication.RAPID_ACTING_INSULIN),
    NOVOLIN_70_30(14, "Novolin 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVOLOG(15, "NovoLog", Medication.RAPID_ACTING_INSULIN),
    NOVOLOG_MIX_70_30(16, "NovoLog Mix 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVORAPID(17, "NovoRapid", Medication.RAPID_ACTING_INSULIN),
    NPH(18, "NPH", Medication.INTERMEDIATE_ACTING_INSULIN),
    TOUJEO(19, "Toujeo", Medication.SHORT_ACTING_INSULIN),
    TRESIBA(20, "Tresiba", Medication.SHORT_ACTING_INSULIN),
    VELOSULIN(21, "Velosulin")
}