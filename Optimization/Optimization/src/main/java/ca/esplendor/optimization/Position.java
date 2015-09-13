package ca.esplendor.optimization;

/**
 * Created by chenzheng on 15-09-10.
 */
public enum Position {
    QB,
    RB,
    WR,
    TE,
    K,
    DEF,
    FLEX;

    static Position fromCode(String code) {
        if (QB.name().equalsIgnoreCase(code)) {
            return QB;
        }
        if (RB.name().equalsIgnoreCase(code)) {
            return RB;
        }
        if (WR.name().equalsIgnoreCase(code)) {
            return WR;
        }
        if (TE.name().equalsIgnoreCase(code)) {
            return TE;
        }
        if (K.name().equalsIgnoreCase(code)) {
            return K;
        }
        if (DEF.name().equalsIgnoreCase(code) || "D".equalsIgnoreCase(code)) {
            return DEF;
        }
        if (FLEX.name().equalsIgnoreCase(code)) {
            return FLEX;
        }
        return null;
    }
}
