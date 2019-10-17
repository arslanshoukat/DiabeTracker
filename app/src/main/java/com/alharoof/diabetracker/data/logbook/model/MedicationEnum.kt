package com.alharoof.diabetracker.data.logbook.model

enum class MedicationEnum(val code: Int, val productName: String, val type: Int = Medication.OTHER) {
    //  bolus insulin
    AFREZZA(100, "Afrezza", Medication.RAPID_ACTING_INSULIN),
    APIDRA(101, "Apidra", Medication.RAPID_ACTING_INSULIN),
    HUMALOG(102, "Humalog", Medication.RAPID_ACTING_INSULIN),
    HUMALOG_MIX_50_50(103, "Humalog Mix 50/50", Medication.RAPID_ACTING_INSULIN),
    HUMALOG_MIX_75_25(104, "Humalog Mix 75/25", Medication.RAPID_ACTING_INSULIN),
    HUMULIN_R(105, "Humulin R", Medication.SHORT_ACTING_INSULIN),
    NOVOLIN_R(106, "Novolin R", Medication.SHORT_ACTING_INSULIN),
    NOVOLOG(107, "NovoLog", Medication.RAPID_ACTING_INSULIN),
    NOVORAPID(108, "NovoRapid", Medication.RAPID_ACTING_INSULIN),
    VELOSULIN(109, "Velosulin", Medication.SHORT_ACTING_INSULIN),

    //  basal insulin
    BASAGLAR(200, "Basaglar", Medication.LONG_ACTING_INSULIN),
    HUMULIN_N(201, "Humulin N", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMULIN_50_50(202, "Humulin 50/50", Medication.INTERMEDIATE_ACTING_INSULIN),
    HUMULIN_70_30(203, "Humulin 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    LANTUS(204, "Lantus", Medication.LONG_ACTING_INSULIN),
    LEVEMIR(205, "Levemir", Medication.LONG_ACTING_INSULIN),
    NOVOLIN_N(206, "Novolin N", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVOLIN_70_30(207, "Novolin 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    NOVOLOG_MIX_70_30(208, "NovoLog Mix 70/30", Medication.INTERMEDIATE_ACTING_INSULIN),
    NPH(209, "NPH", Medication.INTERMEDIATE_ACTING_INSULIN),
    TOUJEO(210, "Toujeo", Medication.LONG_ACTING_INSULIN),
    TRESIBA(211, "Tresiba", Medication.LONG_ACTING_INSULIN),

    //  oral medication
    METFORMIN(300, "Metformin", Medication.DRUGS)
}