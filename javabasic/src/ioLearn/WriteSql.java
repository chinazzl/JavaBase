package ioLearn;

import java.io.*;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/16 15:36
 * @Modified By：
 */
public class WriteSql {

    private static final String DETAILCOMMONSQL = "WITH CORRECT_DET AS\n" +
            " (SELECT\n" +
            "COUNTRY.CITY_NAME CTNAME,\n" +
            "         COUNTRY.CONTRY_NAME CTDVNAME,\n" +
            "         REL.DIM_VALUE_NAME EDNAME,\n" +
            "         ${#p1}\n" +
            "         COUNTRY.CITY_NO\n" +
            "    FROM ${TABLENAME} D\n" +
            "   ${CORRECT_DET_TABLE}\n" +
            "   INNER JOIN SP_DIM_VALUE_INFO REL\n" +
            "      ON D.ORG_NO = REL.DIM_VALUE_CODE\n" +
            "     AND REL.DIM_CODE = '0E0001'\n" +
            "   INNER JOIN SP_DIM_VALUE_INFO PRO_ORG\n" +
            "      ON D.PRO_ORG_NO = PRO_ORG.DIM_VALUE_CODE\n" +
            "     AND PRO_ORG.DIM_CODE = '0E0001'\n" +
            "    LEFT JOIN (SELECT CASE\n" +
            "                       WHEN CONTRY_RELA.TOP_DIM_VALUE_CODE =\n" +
            "                            CONTRY_RELA.DIM_VALUE_CODE AND\n" +
            "                            CONTRY_RELA.DIM_VALUE_CODE LIKE '%A' THEN\n" +
            "                        SUBSTR(CONTRY_RELA.DIM_VALUE_CODE,\n" +
            "                               1,\n" +
            "                               INSTR('A', CONTRY_RELA.DIM_VALUE_CODE) - 1)\n" +
            "                       ELSE\n" +
            "                        CONTRY_RELA.DIM_VALUE_CODE\n" +
            "                     END ORG_NO,\n" +
            "                     CONTRY_NO.DIM_VALUE_NAME CONTRY_NAME,\n" +
            "                     CITY_NO.DIM_VALUE_NAME CITY_NAME,\n" +
            "                     CITY_NO.DIM_VALUE_CODE CITY_NO\n" +
            "                FROM (SELECT DIM_VALUE_CODE\n" +
            "                        FROM SP_DIM_VALUE_INFO\n" +
            "                       WHERE EXTEND_1 = '02'\n" +
            "                         AND DIM_CODE = '0E0001') PRO_ORG,\n" +
            "                     SP_DIM_VALUE_INFO CITY_NO,\n" +
            "                     SP_DIM_VALUE_INFO CONTRY_NO,\n" +
            "                     SP_DIM_VALUE_REL CONTRY_RELA\n" +
            "               WHERE CITY_NO.P_DIM_VALUE = PRO_ORG.DIM_VALUE_CODE\n" +
            "                 AND CITY_NO.DIM_CODE = '0E0007'\n" +
            "                 AND CITY_NO.P_DIM_VALUE <> CITY_NO.DIM_VALUE_CODE\n" +
            "                 AND CONTRY_NO.P_DIM_VALUE = CITY_NO.DIM_VALUE_CODE\n" +
            "                 AND CONTRY_NO.DIM_CODE = '0E0007'\n" +
            "                 AND CONTRY_NO.P_DIM_VALUE <> CONTRY_NO.DIM_VALUE_CODE\n" +
            "                 AND CONTRY_RELA.TOP_DIM_VALUE_CODE =\n" +
            "                     CONTRY_NO.DIM_VALUE_CODE\n" +
            "                 AND CONTRY_RELA.DIM_CODE = '0E0007') COUNTRY\n" +
            "      ON COUNTRY.ORG_NO = D.ORG_NO\n" +
            "   WHERE EXISTS\n" +
            "   (SELECT 1\n" +
            "            FROM SP_DIM_VALUE_REL ORG_RELA\n" +
            "           WHERE D.ORG_NO = (CASE\n" +
            "                WHEN ORG_RELA.TOP_DIM_VALUE_CODE = ORG_RELA.DIM_VALUE_CODE AND\n" +
            "                        ORG_RELA.TOP_DIM_VALUE_CODE LIKE '%A' THEN\n" +
            "                    SUBSTR(ORG_RELA.DIM_VALUE_CODE,\n" +
            "                           1,\n" +
            "                           INSTR(ORG_RELA.DIM_VALUE_CODE, 'A', 1) - 1)\n" +
            "                   ELSE\n" +
            "                    ORG_RELA.DIM_VALUE_CODE\n" +
            "                 END)\n" +
            "             AND ORG_RELA.DIM_CODE = '0E0007'\n" +
            "             AND ORG_RELA.TOP_DIM_VALUE_CODE =\n" +
            "                 SUBSTR(#paraList#, 1, INSTR(#paraList#, '#', 1, 1) - 1))\n" +
            "   ${SEARCHPARAM}\n" +
            "     AND D.STAT_DATE = SUBSTR(#paraList#,\n" +
            "                              INSTR(#paraList#, '#', 1, 2) + 1,\n" +
            "                              INSTR(#paraList#, '#', 1, 3) - 1 -\n" +
            "                              INSTR(#paraList#, '#', 1, 2))\n" +
            "     AND D.ERROR_ID = NVL(NULLIF(UPPER(SUBSTR(#paraList#,\n" +
            "                                              INSTR(#paraList#, '#', 1, 9) + 1,\n" +
            "                                              INSTR(#paraList#, '#', 1, 10) - 1 -\n" +
            "                                              INSTR(#paraList#, '#', 1, 9))),\n" +
            "                                 'NULL'),\n" +
            "                          D.ERROR_ID)\n" +
            "     AND D.APP_NO = NVL(NULLIF(UPPER(SUBSTR(#paraList                                         INSTR(#parR(#paraList#, '#', 1, 7) + 1,\n" +
            "   aList#, '#', 1, 8) - 1 -\n" +
            "                                            INSTR(#paraList#, '#', 1, 7))),\n" +
            "                               'NULL'),\n" +
            "                        D.APP_NO)\n" +
            ")\n" +
            "SELECT ${RESULTSEARCH}\n" +
            "       DATA_DET.ERROR_ID\n" +
            "  FROM (SELECT AA1.SUB_NO,\n" +
            "               AA1.STAT_DATE,\n" +
            "               AA1.SUB_CRUX_INFO,\n" +
            "               AA1.ERROR_ID MAX_ERROR_ID,\n" +
            "               NVL(CC1.ERROR_ID, 0) MAX_APP_ERROR_ID\n" +
            "          FROM DW_SUB_ERROR_CRUX_DET_YM AA1\n" +
            "          LEFT JOIN SP_DIM_VALUE_REL VAL_REL1\n" +
            "            ON AA1.ORG_NO = VAL_REL1.DIM_VALUE_CODE\n" +
            "           AND VAL_REL1.DIM_CODE = '0E0001'\n" +
            "           AND VAL_REL1.TOP_DIM_VALUE_CODE =\n" +
            "               SUBSTR(#paraList#, 1, INSTR(#paraList#, '#', 1, 1) - 1)\n" +
            "          LEFT JOIN BH_SG_TASK_WKST_EXCP_LIST CC1\n" +
            "            ON AA1.SUB_NO = CC1.TOPIC_CODE\n" +
            "           AND AA1.STAT_DATE = CC1.STAT_CYCLE\n" +
            "           AND AA1.ERROR_ID = CC1.ERROR_ID\n" +
            "           AND CC1.GEN_SN = 1\n" +
            "         WHERE EXISTS (SELECT 1\n" +
            "                  FROM P_SUB_INFO SUB_INFO1\n" +
            "                 WHERE SUB_INFO1.SUB_NO = AA1.SUB_NO\n" +
            "                   AND SUB_INFO1.SUB_SCOPE >= '02')\n" +
            "           AND AA1.SUB_NO =\n" +
            "               SUBSTR(#paraList#,\n" +
            "                      INSTR(#paraList#, '#', 1, 10) + 1,\n" +
            "                      INSTR(#paraList#, '#', 1, 11) - 1 -\n" +
            "                      INSTR(#paraList#, '#', 1, 10))) TMP_A\n" +
            " INNER JOIN CORRECT_DET DATA_DET\n" +
            "    ON TMP_A.MAX_ERROR_ID = DATA_DET.ERROR_ID\n" +
            "   AND TMP_A.STAT_DATE = DATA_DET.STAT_DATE\n" +
            " WHERE NOT EXISTS (SELECT 1\n" +
            "          FROM BH_SG_SUB_SUBMIT_DET BB\n" +
            "         WHERE TMP_A.SUB_NO = BB.TOPIC_CODE\n" +
            "           AND TMP_A.MAX_ERROR_ID = BB.ERROR_ID\n" +
            "           AND TMP_A.STAT_DATE = BB.STAT_DATE)\n" +
            "   AND TMP_A.MAX_APP_ERROR_ID = 0\n" +
            "   AND TMP_A.MAX_ERROR_ID >= 1\n" +
            " ORDER BY DATA_DET.ERROR_ID";


    public static void main(String[] args){

    }
}
