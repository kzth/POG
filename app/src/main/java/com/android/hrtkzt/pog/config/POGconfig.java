package com.android.hrtkzt.pog.config;

/**
 * Created by hirotakazuto on 15/06/07.
 */
public class POGconfig {

    public static String getHorseDbName() {
        return HORSE_DB_NAME;
    }
    private static final String HORSE_DB_NAME = "horse";

    public static String getHorseDbTableName() {
        return HORSE_DB_TABLE_NAME;
    }
    private static final String HORSE_DB_TABLE_NAME = "horse_table";

    public static String getIsStableName() {
        return IS_STABLE_NAME;
    }

    public static String getNameName() {
        return NAME_NAME;
    }

    public static String getSexName() {
        return SEX_NAME;
    }

    public static String getMotherName() {
        return MOTHER_NAME;
    }

    public static String getFatherName() {
        return FATHER_NAME;
    }

    public static String getMotherFatherName() {
        return MOTHER_FATHER_NAME;
    }

    public static String getBrotherName() {
        return BROTHER_NAME;
    }

    public static String getOwnerName() {
        return OWNER_NAME;
    }

    public static String getBelongName() {
        return BELONG_NAME;
    }

    public static String getTrainerName() {
        return TRAINER_NAME;
    }

    public static String getProducerName() {
        return PRODUCER_NAME;
    }

    public static String getBirthdayName() {
        return BIRTHDAY_NAME;
    }

    public static String getSaleName() {
        return SALE_NAME;
    }

    public static String getPriceName() {
        return PRICE_NAME;
    }

    public static String getModeName() {
        return MODE_NAME;
    }

    public static String getOneModeName() {
        return ONE_MODE_NAME;
    }

    public static String getClubName() {
        return CLUB_NAME;
    }

    public static String getUpdateName() {
        return UPDATE_NAME;
    }

    private static final String SEX_NAME       = "sex";
    private static final String NAME_NAME      = "name";
    private static final String IS_STABLE_NAME = "is_stable";
    private static final String MOTHER_NAME    = "mother";
    private static final String FATHER_NAME    = "father";
    private static final String MOTHER_FATHER_NAME = "mother_father";
    private static final String BROTHER_NAME   = "brother";
    private static final String OWNER_NAME     = "owner";
    private static final String BELONG_NAME    = "belong";
    private static final String TRAINER_NAME   = "trainer";
    private static final String PRODUCER_NAME  = "producer";
    private static final String BIRTHDAY_NAME  = "birthday";
    private static final String SALE_NAME      = "sale";
    private static final String PRICE_NAME     = "price";
    private static final String MODE_NAME      = "mode";
    private static final String ONE_MODE_NAME  = "one_mode";
    private static final String CLUB_NAME      = "club";
    private static final String UPDATE_NAME    = "updatetime";

    public static String getPreferenceFileName() {
        return PREFERENCE_FILE_NAME;
    }

    private static final String PREFERENCE_FILE_NAME = "horse_pref";

    public static boolean isUpdateFlag() {
        return UPDATE_FLAG;
    }

    private static final boolean UPDATE_FLAG = true;
}
