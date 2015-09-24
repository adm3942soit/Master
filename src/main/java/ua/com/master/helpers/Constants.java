package ua.com.master.helpers;



import ua.com.master.help.FacesHelper;
import ua.com.master.help.Helper;

import javax.faces.model.SelectItem;
import java.util.*;

public class Constants
{
/*
  global constants
*/
    public static Boolean debug;
    public static Boolean debugFormatting;
    public static String accVersion=null;
    public static Boolean accTesting;
    public static Boolean productionVersion;
    public static Boolean devTesting;
/*
    confirmationDialog
*/
    public static Boolean answer=new Boolean(false);
    public static Boolean confirmYes=new Boolean(true);
    public static Boolean confirmNo=new Boolean(false);

/*
    PersonContacts
*/
    public static class CatalogDetails{
    public static final Integer CATALOG_TAB_NUMBER=0;
    public static final Integer DEPARTMENT_TAB_NUMBER=1;
    public static final Integer PRODUCT_TAB_NUMBER=2;
}
    public static class PersonContactTypesDetails
    {
      public static final String ALL_CONTACTS_LABEL="C";
      public static final String ALL_CONTACTS_VALUE="All contacts";

      public static final String PHONE_CONVERSATIONS_LABEL="P";
      public static final String PHONE_CONVERSATIONS_VALUE="Phone conversations";
      public static final String VISITS_LABEL="V";
      public static final String VISITS_VALUE="Person's visits";
      public static final String INTERVIEW_LABEL="I";
      public static final String INTERVIEW_VALUE="Interviews";
      public static final String ASSIGNMENT_AGREEMENTS_LABEL="A";
      public static final String ASSIGNMENT_AGREEMENTS_VALUE="Assignment agreements";
      public static final String EMPLOYMENT_CONTRACTS_LABEL="E";
      public static final String EMPLOYMENT_CONTRACTS_VALUE="Employment contracts";
      public static final String EMAIL_FROM_LABEL="F";
      public static final String EMAIL_FROM_VALUE="Email from person";
      public static final String EMAIL_LABEL="E";
      public static final String EMAIL_VALUE="Email";
      public static final String EMAIL_TO_LABEL="T";
      public static final String EMAIL_TO_VALUE="Email to person";
      public static final String MEETINGS_LABEL="M";
      public static final String MEETINGS_VALUE="Meetings";

      public static String PERSONS_CONTRACTS_TYPES[][] =
                {
                        {ALL_CONTACTS_LABEL, ALL_CONTACTS_VALUE},
                        {PHONE_CONVERSATIONS_LABEL, PHONE_CONVERSATIONS_VALUE},
                        {VISITS_LABEL, VISITS_VALUE},
                        {EMAIL_LABEL, EMAIL_VALUE},
/*
                        {EMAIL_FROM_LABEL, EMAIL_FROM_VALUE},
                        {EMAIL_TO_LABEL, EMAIL_TO_VALUE},
*/
                        {MEETINGS_LABEL, MEETINGS_VALUE}
//                        {INTERVIEW_LABEL, INTERVIEW_VALUE},
//                        {ASSIGNMENT_AGREEMENTS_LABEL, ASSIGNMENT_AGREEMENTS_VALUE},
//                        {EMPLOYMENT_CONTRACTS_LABEL, EMPLOYMENT_CONTRACTS_VALUE}

                };

        public static String getValueTypeByLabel(String value)
        {
            if (Helper.isEmpty(value))
            {
                return "";
            }
            for (String []psnCnt : PERSONS_CONTRACTS_TYPES)
            {
                if(psnCnt[1].equals(value))
                    return psnCnt[0];
            }
            return "";
        }

    }
    public static class CompanyContactTypesDetails
    {
      public static final String ALL_CONTACTS_LABEL="C";
      public static final String ALL_CONTACTS_VALUE="All contacts";

      public static final String PHONE_CONVERSATIONS_LABEL="P";
      public static final String PHONE_CONVERSATIONS_VALUE="Phone conversations";
      public static final String VISITS_LABEL="V";
      public static final String VISITS_VALUE="Person's visits";
      public static final String EMAIL_FROM_LABEL="F";
      public static final String EMAIL_FROM_VALUE="Email from person";
      public static final String EMAIL_TO_LABEL="T";
      public static final String EMAIL_TO_VALUE="Email to person";
      public static final String MEETINGS_LABEL="M";
      public static final String MEETINGS_VALUE="Meetings";

      public static String COMPANY_CONTRACTS_TYPES[][] =
                {
                        {ALL_CONTACTS_LABEL, ALL_CONTACTS_VALUE},
                        {PHONE_CONVERSATIONS_LABEL, PHONE_CONVERSATIONS_VALUE},
                        {VISITS_LABEL, VISITS_VALUE}
/*
                        {EMAIL_FROM_LABEL, EMAIL_FROM_VALUE},
                        {EMAIL_TO_LABEL, EMAIL_TO_VALUE},
                        {MEETINGS_LABEL, MEETINGS_VALUE}
*/


                };

        public static String getValueTypeByLabel(String value)
        {
            if (Helper.isEmpty(value))
            {
                return "";
            }
            for (String []psnCnt : COMPANY_CONTRACTS_TYPES)
            {
                if(psnCnt[1].equals(value))
                    return psnCnt[0];
            }
            return "";
        }

    }


    public static class ArchivingDetails
    {
        public static final String ArhiveDirName= ".cm84archive";
        public static final String RecordsSeparator= "\n\r#";
        public static final String FildsSeparator="~";
        public static final String LocationsFileName="cm84.outLocations";
        public static final String CategoriesFileName="cm84.outCategories";
        public static final String PositionsFileName="cm84.outPositions";
        public static final String ArticlesFileName="cm84.outArticles";
        public static final String RolesFileName="cm84.outRoles";
        public static final String ReportsFileName="cm84.outReports";
        public static final String ResumeDirName=".cm84resumes";


    }

        public static final String STATUS_DRAFT_VALUE = FacesHelper.getBundleMessage("constants_statusdraft");
        public static final String STATUS_PROSPECT_VALUE = FacesHelper.getBundleMessage("constants_statusprospect");
        public static final String STATUS_SUSPECT_VALUE = FacesHelper.getBundleMessage("constants_statussuspect");

        public static final String STATUS_ACTIVE_VALUE = FacesHelper.getBundleMessage("constants_statusactive");

        public static final String STATUS_DRAFT_LABEL = "D";
        public static final String STATUS_PROSPECT_LABEL = "P";
        public static final String STATUS_SUSPECT_LABEL = "S";
        public static final String STATUS_ACTIVE_LABEL = "A";
        public static final String IND_TRIPTICOM_LABEL = "Y";
        public static final String IND_NOT_TRIPTICOM_LABEL = "N";
        public static final String IND_TRIPTICOM_VALUE = "Yes";
        public static final String IND_NOT_TRIPTICOM_VALUE = "No";

        public static final String DISCIPLINE_NOT_AVAILABLE_LABEL = "N";
        public static final String DISCIPLINE_TECHNIQUE_LABEL = "T";
        public static final String DISCIPLINE_ORACLE_JAVA_LABEL = "J";
        public static final String DISCIPLINE_SALES_LABEL = "S";
        public static final String DISCIPLINE_FINANCE_LABEL = "F";
        public static final String DISCIPLINE_ICT_LABEL = "I";

        public static final String DISCIPLINE_NOT_AVAILABLE_VALUE = "N/A";
        public static final String DISCIPLINE_TECHNIQUE_VALUE = FacesHelper.getBundleMessage("constants_disctechn");
        public static final String DISCIPLINE_ORACLE_JAVA_VALUE = FacesHelper.getBundleMessage("constants_discoraclejava");
        public static final String DISCIPLINE_SALES_VALUE = FacesHelper.getBundleMessage("constants_sales");
        public static final String DISCIPLINE_FINANCE_VALUE = FacesHelper.getBundleMessage("constants_finance");
        public static final String DISCIPLINE_ICT_VALUE = "ICT";
        public static final int COMPANY_TAB_NUMBER = 0;
        public static final int ADDRESS_TAB_NUMBER = 1;
        public static final int CONTACTPERSON_TAB_NUMBER = 2;
//        public static final int TRIPTICOM_TAB_NUMBER = 3;


        public static String getStatusValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(STATUS_DRAFT_LABEL))
            {
                return STATUS_DRAFT_VALUE;
            }
            if (label.equals(STATUS_PROSPECT_LABEL))
            {
                return STATUS_PROSPECT_VALUE;
            }
            if (label.equals(STATUS_SUSPECT_LABEL))
            {
                return STATUS_SUSPECT_VALUE;
            }
            if (label.equals(STATUS_ACTIVE_LABEL))
            {
                return STATUS_ACTIVE_VALUE;
            }
            else
            {
                return "";
            }
        }

        public static final String CONTACTPERSON_UNDEFINED_VALUE = "Undefined";




     public static class PersonDetails
    {
        public static final String PASSPORT_TYPE_UNDEFINED_LABEL = "U";
        public static final String PASSPORT_TYPE_UNDEFINED_VALUE =  FacesHelper.getBundleMessage("person_passport_type_undefined");
        public static final String PASSPORT_TYPE_PASSPORT_LABEL = "P";
        public static final String PASSPORT_TYPE_PASSPORT_VALUE =  FacesHelper.getBundleMessage("person_passport_type_passport");
        public static final String PASSPORT_TYPE_CARD_LABEL = "C";
        public static final String PASSPORT_TYPE_CARD_VALUE =  FacesHelper.getBundleMessage("person_passport_type_card");
        public static final String PASSPORT_TYPE_RESIDENCE_LABEL = "R";
        public static final String PASSPORT_TYPE_RESIDENCE_VALUE =  FacesHelper.getBundleMessage("person_passport_type_residence");

        public static String getPassportTypeValueByLabel(String label)
        {
            if(label == null)
            {
                return "";
            }
            if(label.equals(PASSPORT_TYPE_PASSPORT_LABEL))
            {
                return PASSPORT_TYPE_PASSPORT_VALUE;
            }
            if(label.equals(PASSPORT_TYPE_CARD_LABEL))
            {
                return PASSPORT_TYPE_CARD_VALUE;
            }
            if(label.equals(PASSPORT_TYPE_RESIDENCE_LABEL))
            {
                return PASSPORT_TYPE_RESIDENCE_VALUE;
            }
            else
            {
                return PASSPORT_TYPE_UNDEFINED_VALUE;
            }
        }


       private static List<SelectItem> passportTypeselectItems = new ArrayList<SelectItem>();

        public static List<SelectItem> getPassportTypeSelectItems()
        {
            if (Helper.isEmpty(passportTypeselectItems))
            {
                passportTypeselectItems.add(new SelectItem(PASSPORT_TYPE_UNDEFINED_LABEL, PASSPORT_TYPE_UNDEFINED_VALUE));
                passportTypeselectItems.add(new SelectItem(PASSPORT_TYPE_PASSPORT_LABEL, PASSPORT_TYPE_PASSPORT_VALUE));
                passportTypeselectItems.add(new SelectItem(PASSPORT_TYPE_CARD_LABEL, PASSPORT_TYPE_CARD_VALUE));
                passportTypeselectItems.add(new SelectItem(PASSPORT_TYPE_RESIDENCE_LABEL, PASSPORT_TYPE_RESIDENCE_VALUE));
            }
            return passportTypeselectItems;
        }




        public static final String MALE_LABEL = "M";
        public static final String MALE_VALUE = FacesHelper.getBundleMessage("constants_male");
        public static final String FEMALE_LABEL = "F";
        public static final String FEMALE_VALUE = FacesHelper.getBundleMessage("constants_female");


        public static String getGenderValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(MALE_LABEL))
            {
                return MALE_VALUE;
            }
            if (label.equals(FEMALE_LABEL))
            {
                return FEMALE_VALUE;
            }
            else
            {
                return UNDEFINED_VALUE;
            }
        }

        public static String MALE_TITLE = FacesHelper.getBundleMessage("constants_male_title");
        public static String FEMALE_TITLE = FacesHelper.getBundleMessage("constants_female_title");
        public static String UNDEFINED_TITLE = FacesHelper.getBundleMessage("constants_undefined_title");

        public static String getTitleByGender(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(MALE_LABEL))
            {
                return MALE_TITLE;
            }
            if (label.equals(FEMALE_LABEL))
            {
                return FEMALE_TITLE;
            }
            else
            {
                return UNDEFINED_TITLE;
            }
        }

//        public static final String UNDEFINED_VALUE = FacesHelper.getBundleMessage("constants_undefined");
//        public static final String UNDEFINED_LABEL = "U";

        public static final String INIT_USER = "init system";

        public static final String TECHNIQUE_LABEL = "T";
        public static final String TECHNIQUE_VALUE = FacesHelper.getBundleMessage("constants_technique");
        public static final String JAVA_LABEL = "J";
        public static final String JAVA_VALUE = FacesHelper.getBundleMessage("constants_java");
        public static final String FINANCE_LABEL = "F";
        public static final String FINANCE_VALUE = FacesHelper.getBundleMessage("constants_finance");
        public static final String SALES_LABEL = "S";
        public static final String SALES_VALUE = FacesHelper.getBundleMessage("constants_sales");
        public static final String ICT_LABEL = "I";
        public static final String ICT_VALUE = FacesHelper.getBundleMessage("constants_ict");
        public static final String UNDEFINED_LABEL = "U";
        public static final String UNDEFINED_VALUE = FacesHelper.getBundleMessage("constants_undefined");

        public static String getDisciplineValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(TECHNIQUE_LABEL))
            {
                return TECHNIQUE_VALUE;
            }
            if (label.equals(JAVA_LABEL))
            {
                return JAVA_VALUE;
            }
            if (label.equals(FINANCE_LABEL))
            {
                return FINANCE_VALUE;
            }
            if (label.equals(SALES_LABEL))
            {
                return SALES_VALUE;
            }
            if (label.equals(ICT_LABEL))
            {
                return ICT_VALUE;
            }
            else
            {
                return UNDEFINED_VALUE;
            }
        }


        public static final String LBO_LABEL = "L";
        public static final String LBO_VALUE = FacesHelper.getBundleMessage("constants_lbo");
        public static final String MBO_LABEL = "M";
        public static final String MBO_VALUE = FacesHelper.getBundleMessage("constants_mbo");
        public static final String HBO_LABEL = "H";
        public static final String HBO_VALUE = FacesHelper.getBundleMessage("constants_hbo");
        public static final String WO_LABEL = "W";
        public static final String WO_VALUE = FacesHelper.getBundleMessage("constants_wo");

        public static String getEducationValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(MBO_LABEL))
            {
                return MBO_VALUE;
            }
            if (label.equals(HBO_LABEL))
            {
                return HBO_VALUE;
            }
            if (label.equals(WO_LABEL))
            {
                return WO_VALUE;
            }
            else
            {
                return UNDEFINED_VALUE;
            }
        }


        public static final int PERSON_TAB_NUMBER = 0;
        public static final int ADDRESS_TAB_NUMBER = 1;
        public static final int CERTIFICATE_TAB_NUMBER = 2;
        public static final int POSITIONS_TAB_NUMBER = 3;
        public static final int ACTIONS_TAB_NUMBER = 4;
        public static final int INTERVIEWS_TAB_NUMBER = 5;
        public static final int REQUEST_TAB_NUMBER = 6;
        public static final int DOCUMENTS_TAB_NUMBER = 7;

        public static final String CORRESPONDENCE_LABEL = "C";
        public static final String CORRESPONDENCE_VALUE = FacesHelper.getBundleMessage("constants_correspondence");
        public static final String INVOICE_LABEL = "V";
        public static final String INVOICE_VALUE = FacesHelper.getBundleMessage("constants_invoice");
        public static final String NURSING_LABEL = "I";
        public static final String NURSING_VALUE = FacesHelper.getBundleMessage("nursing");
        public static final String HOME_LABEL = "H";
        public static final String HOME_VALUE = FacesHelper.getBundleMessage("constants_home");
        public static final String UND_TYPE_LABEL = "U";
        public static final String UND_TYPE_VALUE = FacesHelper.getBundleMessage("sel_type");


        public static String getTypeValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(CORRESPONDENCE_LABEL))
            {
                return CORRESPONDENCE_VALUE;
            }
            if (label.equals(NURSING_LABEL))
            {
                return NURSING_VALUE;
            }
            if (label.equals(HOME_LABEL))
            {
                return HOME_VALUE;
            }
            if (label.equals(UND_TYPE_LABEL))
            {
                return UND_TYPE_VALUE;
            }
            else
            {
                return "";
            }
        }
        //added by ""
//     public static final String SELECT_VALUE=FacesHelper.getBundleMessage("select_value");
//     public static final String PASPORT_TYPE_DOC_LABEL="P";
//     public static final String PASPORT_TYPE_DOC_VALUE=FacesHelper.getBundleMessage("psp_type");
//     public static final String IDENT_TYPE_DOC_LABEL="I";
//     public static final String IDENT_TYPE_DOC_VALUE=FacesHelper.getBundleMessage("ident_type");
//     public static final String VERB_TYPE_DOC_LABEL="I";
//     public static final String VERB_TYPE_DOC_VALUE=FacesHelper.getBundleMessage("verb_type");
//     public static final String[][] TYPE_DOC=
//             {
//                     {UNDEFINED_LABEL, SELECT_VALUE},
//                     {PASPORT_TYPE_DOC_LABEL, PASPORT_TYPE_DOC_VALUE},
//                     {IDENT_TYPE_DOC_LABEL, IDENT_TYPE_DOC_VALUE},
//                     {VERB_TYPE_DOC_LABEL, VERB_TYPE_DOC_VALUE}
//             };
//      public static String getTypeDocValueByLabel(String label)
//      {
//         if(CMHelper.isEmpty(label))return "";
//         for(String[] strokaTypes:TYPE_DOC)
//         {
//           if(strokaTypes[0].equals(label.trim()))
//           {
//              return strokaTypes[1];
//           }
//         }
//       return "";
//      }
//      public static String getTypeDocLabelByValue(String value)
//      {
//          if(CMHelper.isEmpty(value))return "";
//           for(String[] strokaTypes:TYPE_DOC)
//           {
//             if(strokaTypes[1].equals(value.trim()))
//             {
//                return strokaTypes[0];
//             }
//           }
//         return "";
//        }
//       public static List<SelectItem> getListTypesDoc()
//       {
//         List<SelectItem> list=new ArrayList<SelectItem>();
//           for(String[] strokaTypes:TYPE_DOC)
//           {
//                list.add(new SelectItem(strokaTypes[0], strokaTypes[1]));
//           }
//        return list;
//       }

    }

     public static class PersonStatusDetails
    {
        public static final String DRAFT_LABEL = "D";
        public static final String REJECT_LABEL = "R";
        public static final String SUSPECT_LABEL = "S";
        public static final String PROSPECT_LABEL = "P";
        public static final String DRAFT_VALUE = "Draft";
        public static final String REJECT_VALUE = "Reject";
        public static final String SUSPECT_VALUE = "Suspect";
        public static final String PROSPECT_VALUE = "Prospect";
        public static final String CONTACT_PERSON_LABEL = "C";

        /*added Nastasyuk*/
        public static List<SelectItem> getPersonStatusList()
        {
            List<SelectItem> personStatusList = new ArrayList<SelectItem>();
            personStatusList.add(new SelectItem(PersonDetails.UNDEFINED_LABEL, PersonDetails.UNDEFINED_VALUE));
            personStatusList.add(new SelectItem(PersonStatusDetails.PROSPECT_LABEL, PersonStatusDetails.PROSPECT_VALUE));
            personStatusList.add(new SelectItem(PersonStatusDetails.REJECT_LABEL, PersonStatusDetails.REJECT_VALUE));
            personStatusList.add(new SelectItem(PersonStatusDetails.SUSPECT_LABEL,PersonStatusDetails.SUSPECT_VALUE));
            personStatusList.add(new SelectItem(PersonStatusDetails.DRAFT_LABEL, PersonStatusDetails.DRAFT_VALUE));
            return personStatusList;
        }

        public static String getStatusValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(DRAFT_LABEL))
            {
                return DRAFT_VALUE;
            }
            if (label.equals(REJECT_LABEL))
            {
                return REJECT_VALUE;
            }
            if (label.equals(SUSPECT_LABEL))
            {
                return SUSPECT_VALUE;
            }
            if (label.equals(PROSPECT_LABEL))
            {
                return PROSPECT_VALUE;
            }
            else
            {
                return "";
            }
        }

    }

     public static class PersonActionDetails
    {
        public static final String OPEN_LABEL = "O";
        public static final String CLOSE_LABEL = "C";
        public static final String OPEN_VALUE = "Open";
        public static final String CLOSE_VALUE = "Close";

        public static String getStatusValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(OPEN_LABEL))
            {
                return OPEN_VALUE;
            }
            if (label.equals(CLOSE_LABEL))
            {
                return CLOSE_VALUE;
            }
            else
            {
                return "";
            }
        }

        public static List<SelectItem> getActionStatusList()
        {
            List<SelectItem> l = new ArrayList<SelectItem>();
            l.add(new SelectItem(PersonActionDetails.OPEN_LABEL, PersonActionDetails.OPEN_VALUE));
            l.add(new SelectItem(PersonActionDetails.CLOSE_LABEL, PersonActionDetails.CLOSE_VALUE));
            return l;
        }

    }

 public static class CompanyRoleDetails
    {
        public static final String DIRECTOR_LABEL = "D";
        public static final String DIRECTOR_VALUE = FacesHelper.getBundleMessage("constants_director");
        public static final String PLANNER_LABEL = "L";
        public static final String PLANNER_VALUE = FacesHelper.getBundleMessage("constants_planner");
        public static final String HRREPRESENTATIVE_LABEL = "H";
        public static final String HRREPRESENTATIVE_VALUE = "Hrrepresentative";//FacesHelper.getBundleMessage("constants_hrrepresentative");
        public static final String PROJECT_LEADER_LABEL = "P";
        public static final String PROJECT_LEADER_VALUE = FacesHelper.getBundleMessage("constants_projectleader");

        public static String COMPANYROLE[][] =
                {
                        {DIRECTOR_LABEL, DIRECTOR_VALUE},
                        {PLANNER_LABEL, PLANNER_VALUE},
                        {HRREPRESENTATIVE_LABEL, HRREPRESENTATIVE_VALUE},
                        {PROJECT_LEADER_LABEL, PROJECT_LEADER_VALUE}
                };

        public static String getLabelValueByRole(String value)
        {
            if (Helper.isEmpty(value))
            {
                return "";
            }
            for (String role[] : COMPANYROLE)
            {
                if (value.toLowerCase().equals(role[1].toLowerCase()))
                {
                    return role[0];
                }
            }
            return "";
        }

        public static String getRoleValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(DIRECTOR_LABEL))
            {
                return DIRECTOR_VALUE;
            }
            if (label.equals(PLANNER_LABEL))
            {
                return PLANNER_VALUE;
            }
            if (label.equals(HRREPRESENTATIVE_LABEL))
            {
                return HRREPRESENTATIVE_VALUE;
            }
            if (label.equals(PROJECT_LEADER_LABEL))
            {
                return PROJECT_LEADER_VALUE;
            }
            else
            {
                return "";
            }
        }
    }


    public static class RoleDetails
    {
        public static final String CREDIT_CHECKER_LABEL = "CRCH";
        public static final String REGISTRAR_LABEL = "RNBD";
        public static final String TRIPTICOM_DIRECTOR_LABEL = "TRDI";
        public static final String REGISTRAR_PERSON_LABEL = "RNPD";
        public static final String CREDIT_CHECKER_VALUE = FacesHelper.getBundleMessage("constants_creditchecker");
        public static final String REGISTRAR_VALUE = FacesHelper.getBundleMessage("constants_regnewcomp");
        public static final String TRIPTICOM_DIRECTOR_VALUE = FacesHelper.getBundleMessage("constants_director");
        public static final String REGISTRAR_PERSON_VALUE = FacesHelper.getBundleMessage("constants_registrar_person");
        public static final String ACCEPTOR_NEW_PERSON_LABEL = "RNPD";
        public static String ACCEPTOR_NEW_PERSON_VALUE = FacesHelper.getBundleMessage("constants_acceptor_new_person");
        public static final String INTERVIEWER_LABEL = "INWR";
        public static final String INTERVIEWER_VALUE = FacesHelper.getBundleMessage("constants_interviewer_person");
        public static final String NOTTRIPTICOM_LABEL = "NTTR";
        public static final String NOTTRIPTICOM_VALUE = FacesHelper.getBundleMessage("not_tripticom");
        // Register Request
        public static final String REGISTRAR_REQUEST_LABEL = "RNRD";
        public static final String REGISTRAR_REQUEST_VALUE = FacesHelper.getBundleMessage("constants_registrar_request");

        // Register assignment agreement
        public static final String REGISTRAR_ASSIGNMENT_AGREEMENT_LABEL = "RAAD";
        public static final String REGISTRAR_ASSIGNMENT_AGREEMENT_VALUE = FacesHelper.getBundleMessage("registrar_ass_agrmnt");
        //added ONastasyuk
        public static final String ADMIN_COMPANY_LABEL = "ADCP";
        public static final String ADMIN_COMPANY_VALUE = FacesHelper.getBundleMessage("admin_company");

        public static final String ADMIN_PERSON_LABEL = "ADPS";
        public static final String ADMIN_PERSON_VALUE = FacesHelper.getBundleMessage("admin_person");

        public static final String ADMIN_ANOTHER_LABEL = "ADAN";
        public static final String ADMIN_ANOTHER_VALUE = FacesHelper.getBundleMessage("admin_another");

        public static final String ADMIN_VIEW_PERSON_LABEL = "AVPS";
        public static final String ADMIN_VIEW_PERSON_VALUE = FacesHelper.getBundleMessage("admin_view_person");

        public static final String ADMIN_VIEW_COMPANY_LABEL = "AVCP";
        public static final String ADMIN_VIEW_COMPANY_VALUE = FacesHelper.getBundleMessage("admin_view_company");

        public static final String EMPLOYMENT_CONTRACT_HR_LABEL = "ECHR";
        public static final String EMPLOYMENT_CONTRACT_HR_VALUE = FacesHelper.getBundleMessage("emp_con_hr");

        public static final String EMPLOYMENT_CONTRACT_BU_LABEL = "ECBU";
        public static final String EMPLOYMENT_CONTRACT_BU_VALUE = FacesHelper.getBundleMessage("bu_director");

        public static final String EMPLOYMENT_CONTRACT_DIRECTOR_LABEL = "ECDR";
        public static final String EMPLOYMENT_CONTRACT_DIRECTOR_VALUE = FacesHelper.getBundleMessage("tr_director");

        public static final String EMPLOYMENT_CONTRACT_PROCESS_ADMIN_LABEL="ECPA";
        public static final String EMPLOYMENT_CONTRACT_PROCESS_ADMIN_VALUE = FacesHelper.getBundleMessage("ec_process_admin");


        public static final String EMPLOYMENT_CONTRACT_ADMIN_LABEL="ECAL";
        public static final String EMPLOYMENT_CONTRACT_ADMIN_VALUE = FacesHelper.getBundleMessage("admin_contract");

        public static final String ASSIGNMENT_AGREEMENT_ADMIN_LABEL="AAAL";
        public static final String ASSIGNMENT_AGREEMENT_ADMIN_VALUE = FacesHelper.getBundleMessage("admin_agreement");

        public static final String REGISTRAR_INTERVIEW_LABEL="RINT";
        public static final String REGISTRAR_INTERVIEW_VALUE=FacesHelper.getBundleMessage("reg_inter_role");


        public static final String ADMINISTRATION_DATABASE_LABEL="ADDB";
        public static final String ADMINISTRATION_DATABASE_VALUE=FacesHelper.getBundleMessage("admin_db");

        public static final String PERSON_CONTACT_MANAGEMENT_LABEL="PCMN";
        public static final String PERSON_CONTACT_MANAGEMENT_VALUE=FacesHelper.getBundleMessage("psn_cnt_mngmnt");

        public static final String COMPANY_CONTACT_MANAGEMENT_LABEL="CCMN";
        public static final String COMPANY_CONTACT_MANAGEMENT_VALUE=FacesHelper.getBundleMessage("cmp_cnt_mngmnt");
        public static final String REGISTRATION_RESUME_LABEL="RRES";
        public static final String REGISTRATION_RESUME_VALUE=FacesHelper.getBundleMessage("reg_resume");
        public static final String PR_OFFICER_LABEL="RROF";
        public static final String PR_OFFICER_VALUE=FacesHelper.getBundleMessage("pr_officer");

        public static String getRoleValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if(label.equals(PR_OFFICER_LABEL)){
                return PR_OFFICER_VALUE;
            }
            if (label.equals(PERSON_CONTACT_MANAGEMENT_LABEL))
            {
                return PERSON_CONTACT_MANAGEMENT_VALUE;
            }
            if (label.equals(COMPANY_CONTACT_MANAGEMENT_LABEL))
            {
                return COMPANY_CONTACT_MANAGEMENT_VALUE;
            }

            if (label.equals(REGISTRAR_INTERVIEW_LABEL))
            {
                return REGISTRAR_INTERVIEW_VALUE;
            }

            if (label.equals(ASSIGNMENT_AGREEMENT_ADMIN_LABEL))
            {
                return ASSIGNMENT_AGREEMENT_ADMIN_VALUE;
            }

            if (label.equals(EMPLOYMENT_CONTRACT_ADMIN_LABEL))
            {
                return EMPLOYMENT_CONTRACT_ADMIN_VALUE;
            }

            if (label.equals(EMPLOYMENT_CONTRACT_HR_LABEL))
            {
                return EMPLOYMENT_CONTRACT_HR_VALUE;
            }

            if (label.equals(EMPLOYMENT_CONTRACT_BU_LABEL))
            {
                return EMPLOYMENT_CONTRACT_BU_VALUE;
            }
            if (label.equals(EMPLOYMENT_CONTRACT_DIRECTOR_LABEL))
            {
                return EMPLOYMENT_CONTRACT_DIRECTOR_VALUE;
            }

            if (label.equals(CREDIT_CHECKER_LABEL))
            {
                return CREDIT_CHECKER_VALUE;
            }
            if (label.equals(REGISTRAR_LABEL))
            {
                return REGISTRAR_VALUE;
            }
            if (label.equals(TRIPTICOM_DIRECTOR_LABEL))
            {
                return TRIPTICOM_DIRECTOR_VALUE;
            }
            if (label.equals(REGISTRAR_PERSON_LABEL))
            {
                return REGISTRAR_PERSON_VALUE;
            }
            if (label.equals(ACCEPTOR_NEW_PERSON_LABEL))
            {
                return ACCEPTOR_NEW_PERSON_VALUE;
            }
            if (label.equals(INTERVIEWER_LABEL))
            {
                return INTERVIEWER_VALUE;
            }
            if (label.equals(REGISTRAR_ASSIGNMENT_AGREEMENT_LABEL))
            {
                return REGISTRAR_ASSIGNMENT_AGREEMENT_VALUE;
            }
            if (label.equals(NOTTRIPTICOM_LABEL))
            {
                return NOTTRIPTICOM_VALUE;
            }
            if (label.equals(ADMIN_COMPANY_LABEL))
            {
                return ADMIN_COMPANY_VALUE;
            }
            if (label.equals(ADMIN_PERSON_LABEL))
            {
                return ADMIN_PERSON_VALUE;
            }
            if (label.equals(ADMIN_ANOTHER_LABEL))
            {
                return ADMIN_ANOTHER_VALUE;
            }
            if (label.equals(ADMIN_VIEW_PERSON_LABEL))
            {
                return ADMIN_VIEW_PERSON_VALUE;
            }
            if (label.equals(ADMIN_VIEW_COMPANY_LABEL))
            {
                return ADMIN_VIEW_COMPANY_VALUE;
            }
            else
            {
                return "";
            }
        }
    }


    public static class MessageDetails
    {
        public static final String READ_STATUS_LABEL = "R";
        public static final String READ_STATUS_VALUE = FacesHelper.getBundleMessage("constants_read");
        public static final String UNREAD_STATUS_LABEL = "U";
        public static final String UNREAD_STATUS_VALUE = FacesHelper.getBundleMessage("constants_unread");
        public static final String DELETED_STATUS_LABEL = "D";
        public static final String DELETED_STATUS_VALUE = FacesHelper.getBundleMessage("constants_deleted");
        public static final String EMPLOYMENT_CONTRACT_TYPE_LABEL="E";
        public static final String REGISTER_COMPANY_TYPE_LABEL="C";
        public static final String REGISTER_INTERVIEW_TYPE_LABEL="I";
        public static final String REQUEST_CANDIDATE_TYPE_LABEL="R";
        public static final String ASSIGNMENT_AGREEMENT_TYPE_LABEL="A";

    }

    public static class CreditDecisionDetails
    {
        public static final String ACCEPT_DECISION_LABEL = "A";
        public static final String ACCEPT_DECISION_VALUE = FacesHelper.getBundleMessage("accepted");
        public static final String REJECT_DECISION_LABEL = "R";
        public static final String REJECT_DECISION_VALUE = FacesHelper.getBundleMessage("rejected");
        public static final String DIRECTOR_REJECT_DECISION_VALUE = FacesHelper.getBundleMessage("overruled");

        public static String getDecisionMessageByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(ACCEPT_DECISION_LABEL))
            {
                return ACCEPT_DECISION_VALUE;
            }
            if (label.equals(REJECT_DECISION_LABEL))
            {
                return REJECT_DECISION_VALUE;
            }
            else
            {
                return "";
            }
        }

        public static String getDirectorDecisionMessageByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(ACCEPT_DECISION_LABEL))
            {
                return ACCEPT_DECISION_VALUE;
            }
            if (label.equals(REJECT_DECISION_LABEL))
            {
                return DIRECTOR_REJECT_DECISION_VALUE;
            }
            else
            {
                return "";
            }
        }

    }

     public static class ProcessDetails
    {
        public static final String STATE_ONGOING = "G";
        public static final String STATE_ONHOLD = "H";
        public static final String STATE_FINISHED = "F";

        public static final String SEARCH_STATE_FINISHED_LABEL = "FN";
        public static final String SEARCH_STATE_ACTIVE_LABEL = "AC";
        public static final String SEARCH_STATE_ACTIVE_AND_FINISHED_LABEL = "AF";
        public static final String SEARCH_STATE_CURRENT_STEP_LABEL = "CS";
        public static final String SEARCH_STATE_FINISHED_VALUE = "Finished";
        public static final String SEARCH_STATE_ACTIVE_VALUE = "Active";
        public static final String SEARCH_STATE_ACTIVE_AND_FINISHED_VALUE = "All";
        public static final String SEARCH_STATE_CURRENT_STEP_VALUE = "Current";

        public static final String OPEN_STATUS_LABEL = "O";
        public static final String FINISHED_STATUS_LABEL = "F";

        public static final String OPEN_STATUS_VALUE = FacesHelper.getBundleMessage("process_opened");
        public static final String FINISHED_STATUS_VALUE = FacesHelper.getBundleMessage("process_finished");

        public static final String CHOSEN_STATUS_VALUE = FacesHelper.getBundleMessage("process_chosen");
        public static final String CHOSEN_STATUS_LABEL = "H";

        public static final String SCHEDULED_STATUS_VALUE = FacesHelper.getBundleMessage("process_scheduled");
        public static final String SCHEDULED_STATUS_LABEL = "S";

        public static final String CANCELED_STATUS_VALUE = FacesHelper.getBundleMessage("process_canceled");
        public static final String CANCELED_STATUS_LABEL = "C";

        public static final String REPORTED_STATUS_VALUE = FacesHelper.getBundleMessage("process_reported");
        public static final String REPORTED_STATUS_LABEL = "R";

        public static final String TOBEREPORTED_STATUS_VALUE = FacesHelper.getBundleMessage("process_tobereported");
        public static final String TOBEREPORTED_STATUS_LABEL = "T";


        public static String getStatusMessageByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(OPEN_STATUS_LABEL))
            {
                return OPEN_STATUS_VALUE;
            }
            if (label.equals(FINISHED_STATUS_LABEL))
            {
                return FINISHED_STATUS_VALUE;
            }
            if (label.equals(SCHEDULED_STATUS_LABEL))
            {
                return SCHEDULED_STATUS_VALUE;
            }
            if (label.equals(CANCELED_STATUS_LABEL))
            {
                return CANCELED_STATUS_VALUE;
            }
            if (label.equals(REPORTED_STATUS_LABEL))
            {
                return REPORTED_STATUS_VALUE;
            }
            if (label.equals(TOBEREPORTED_STATUS_LABEL))
            {
                return TOBEREPORTED_STATUS_VALUE;
            }
            if (label.equals(CHOSEN_STATUS_LABEL))
            {
                return CHOSEN_STATUS_VALUE;
            }
            else
            {
                return "";
            }
        }


    }

    public static class PublishDetails
    {
        private static final Map charactersMap = new HashMap();


    }


    public static class StateDetails
    {
        public static final String State[][] =
                {{"ZZ", "Unknown or unspecified state"},
                        {"AL", "Alabama"},
                        {"AK", "Alaska"},
                        {"AZ", "Arizona"},
                        {"AR", "Arkansas"},
                        {"CA", "California"},
                        {"CO", "Colorado"},
                        {"CT", "Connecticut"},
                        {"DC", "D. C."},
                        {"DE", "Delaware"},
                        {"FL", "Florida"},
                        {"GA", "Georgia"},
                        {"HI", "Hawaii"},
                        {"ID", "Idaho"},
                        {"IL", "Illinois"},
                        {"IN", "Indiana"},
                        {"IA", "Iowa"},
                        {"KS", "Kansas"},
                        {"KY", "Kentucky"},
                        {"LA", "Louisiana"},
                        {"ME", "Maine"},
                        {"MD", "Maryland"},
                        {"MA", "Massachusetts"},
                        {"MI", "Michigan"},
                        {"MN", "Minnesota"},
                        {"MS", "Mississippi"},
                        {"MO", "Missouri"},
                        {"MT", "Montana"},
                        {"NE", "Nebraska"},
                        {"NV", "Nevada"},
                        {"NH", "New Hampshire"},
                        {"NJ", "New Jersey"},
                        {"NM", "New Mexico"},
                        {"NY", "New York"},
                        {"NC", "North Carolina"},
                        {"ND", "North Dakota"},
                        {"OH", "Ohio"},
                        {"OK", "Oklahoma"},
                        {"OR", "Oregon"},
                        {"PA", "Pennsylvania"},
                        {"RI", "Rhode Island"},
                        {"SC", "South Carolina"},
                        {"SD", "South Dakota"},
                        {"TN", "Tennessee"},
                        {"TX", "Texas"},
                        {"UT", "Utah"},
                        {"VT", "Vermont"},
                        {"VA", "Virginia"},
                        {"WA", "Washington"},
                        {"WV", "West Virginia"},
                        {"WI", "Wisconsin"},
                        {"WY", "Wyoming"},
                        {"AS", "American Samoa"},
                        {"CZ", "Canal Zone"},
                        {"GU", "Guam"},
                        {"MY", "Military"},
                        {"PR", "Puerto Rico"},
                        {"VI", "U.S. Virgin Islands"},
                        {"AB", "Alberta"},
                        {"BC", "British Columbia"},
                        {"MB", "Manitoba"},
                        {"NB", "New Brunswick"},
                        {"NF", "Newfoundland"},
                        {"NT", "NW Territory"},
                        {"NS", "Nova Scotia"},
                        {"ON", "Ontario"},
                        {"PE", "Price Edward Is."},
                        {"PQ", "Quebec"},
                        {"SK", "Saskatchewan"},
                        {"YT", "Yukon Territory"},
                        {"RA", "Resident Alien"}


                };

        public static String findStateOnCode(String code)
        {
            String state = "";
            for (String lit[] : StateDetails.State)
            {
                if (lit[0].equals(code.trim()))
                {
                    state = lit[1].trim();
                    return state;
                }

            }
            return state;
        }

        public static String findCodeOnState(String state)
        {
            String code = "ZZ";
            for (String lit[] : StateDetails.State)
            {
                if (lit[1].equals(state.trim()))
                {
                    code = lit[0].trim();
                    return code;
                }

            }
            return code;
        }

    }

     public static class StepDetails
    {
        public static final String ENTER_COMPANY_DETAILS_STEP = "ENCD";
        public static final String CREDIT_CHECK_STEP = "CRCK";
        public static final String PASSED_CHECK_STEP = "PSCH";
        public static final String REGISTER_CREDIT_LIMIT_CHECKER_STEP = "RGCC";
        public static final String REGISTER_CREDIT_LIMIT_DIRECTOR_STEP = "RGCD";
        public static final String MOTIVATE_REJECTION_STEP = "MTRJ";
        public static final String REJECTION_CREATED_STEP = "RJCR";
        public static final String DIRECTOR_MOTIVATE_ACCEPT_STEP = "DMTA";
        public static final String DIRECTOR_MOTIVATE_OVERRULE_STEP = "DMTO";
        public static final String FINISH_COMPANY_REGISTRATION_STEP = "FCRG";
        // Register person process
        public static final String RP_REGISTRATION_PERSON_PROCESS_OWNER_STEP = "RPPO";
        public static final String RP_REGISTRATION_PERSON_DETAILS_STEP = "RPSD";
        public static final String RP_INTERVIEWS_STEP = "INWS";
        public static final String RP_EDIT_PERSON_DETAILS_STEP = "EDPD";
        public static final String RP_REJECT_PERSON_STEP = "RGPS";
        public static final String RP_ACCEPT_PERSON_STEP = "ACPS";
        public static final String RP_PROSPECT_PERSON_STEP = "PRPS";
        public static final String RP_PUBLICATION_STEP = "PRPU";
        public static final String RP_FINISHED_PERSON_STEP = "FIPS";
        // Interview process
        public static final String REGISTER_INTERVIEW_REPORT_STEP = "RIWR";
        public static final String FINISH_INTERVIEW_STEP = "FINW";

        //Register Request
        public static final String ENTER_REQUEST_DETAILS_STEP = "ENRD";
        public static final String SEARCH_CANDIDATE_STEP = "SCHC";
        public static final String CLOSE_REQUEST_STEP = "CLSR";
        public static final String CANDIDATE_STATUS_STEP = "CNDS";
        public static final String CLOSED_REQUEST_STEP = "CLDR";
        public static final String FINISHED_REQUEST_STEP = "FNSR";
        public static final String REGISTER_REQUEST_CANDIDATES_STEP = "RGRC";
        //Register assignment agreement
        public static final String AA_SEARCH_ASSIGNMENT_AGREEMENT_STEP = "SAAS";
        public static final String AA_ENTER_ASSIGNMENT_AGREEMENT_DETAILS_STEP = "ENAD";
        public static final String AA_CUSTOMER_ASSIGNMENT_AGREEMENT_STEP = "CSAA";
        public static final String AA_DISAGREEMENT_ACCEPT_ASSIGNMENT_AGREEMENT_STEP = "DAAA";
        public static final String AA_DISAGREEMENT_REJECT_ASSIGNMENT_AGREEMENT_STEP = "DRAA";
        public static final String AA_SUCCESS_ASSIGNMENT_AGREEMENT_STEP = "SCAA";
        public static final String AA_FINISH_ASSIGNMENT_AGREEMENT_STEP = "FAAS";
        public static final String AA_UNSUCCESSFULLY_FINISH_ASSIGNMENT_AGREEMENT_STEP = "UFAS";
        public static final String AA_CONTRACT_RETURNED_SIGNED = "CRTS";
        public static final String AA_REJECT_AGREEMENT = "ARJA";

        //Register employment contract
        public static final String EC_FIND_PERSON_STEP = "FPSS";
        public static final String EC_PASSPORT_CHECK_STEP = "PSCS";
        public static final String EC_PASSPORT_REGISTER_DETAILS_STEP = "PRDS";
        public static final String EC_CONTRACT_VALUES_MANAGER_STEP = "CVMS";
        public static final String EC_CONTRACT_DETAILS_STEP = "ECDS";
        public static final String EC_CONTRACT_REPORT_DETAILS_STEP = "ECRS";
        public static final String EC_CONTRACT_FINAL_REPORT_DETAILS_STEP = "EFRS";
        public static final String EC_DIRECTOR_APPROVAL_STEP = "DRAS";
        public static final String EC_APPROVED_STEP = "APRS";
        public static final String EC_FINAL_APPROVAL_STEP="FNAP";
        public static final String EC_CONTRACT_SIGNED_STEP = "CSGN";
        public static final String EC_FINAL_REJECTION_STEP = "FRJS";
        public static final String EC_FINAL_REJECTION_CONCEPT_STEP = "FRCS";
        public static final String EC_SUCCESFULLY_FINISH_STEP = "FECS";
        public static final String EC_UNSUCCESFULLY_FINISH_STEP = "UFCT";

        public static final String INT_FIND_PERSON_STEP="IFPS";
        public static final String INT_SHEDULE_INTERVIEWS_STEP = "SINT";
        public static final String INT_FINISH_SHEDULE_INTERVIEWS_STEP="FSHI";
        public static final String INT_REJECT_PERSON_STEP = "RGPI";
        public static final String INT_ACCEPT_PERSON_STEP = "ACPI";
        public static final String INT_PROSPECT_PERSON_STEP = "PRPI";
        public static final String INT_PUBLICATION_STEP = "PRPX";

        public static final String RR_FIND_PERSON_STEP="RRFP";
        public static final String RR_CHECK_PASSPORT="CHPS";
        public static final String RR_PASSPORT_EDIT="RRPE";
        public static final String RR_APPROVAL_DOCUMENT="RRAD";
        public static final String RR_SELECT_RESUME_FROM_FILE="SRFF";
        public static final String RR_LOAD_RESUME_FROM_FILE="SLRF";
        public static final String RR_REGISTER_PERSON_DETAILS_STEP="RPDR";
        public static final String RR_REGISTER_EDUCATION_AND_COURSES_STEP="RECR";
        public static final String RR_REGISTER_KNOWLEDGE_MATRIX_STEP="RKMR";
        public static final String RR_REGISTER_LANGUAGES_STEP="RLST";
        public static final String RR_REGISTER_WORK_EXPERIENCE_DETAILS="RWED";
        public static final String RR_REGISTER_SUMMARY_STEP="RSSR";
        public static final String RR_PUBLISH_RESUME_ON_SITE="PROS";
        public static final String RR_PRINT_RESUME="PRES";
        public static final String RR_FINISH_STEP="RRFS";



        public static String SELECT_VALUE_LABEL = "O";
        public static String SELECT_VALUE_STEP = "Select process / Show all";//FacesHelper.getBundleMessage("select_value");
        public static String SELECT_VALUE_PROCESS = "Select process";

        public static String STEPS[][] = {
        {SELECT_VALUE_LABEL, SELECT_VALUE_PROCESS, SELECT_VALUE_LABEL},
        {ENTER_COMPANY_DETAILS_STEP, getProcessMessageByLabel(ENTER_COMPANY_DETAILS_STEP, true),  ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {CREDIT_CHECK_STEP, getProcessMessageByLabel(CREDIT_CHECK_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {PASSED_CHECK_STEP, getProcessMessageByLabel(PASSED_CHECK_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {REGISTER_CREDIT_LIMIT_CHECKER_STEP, getProcessMessageByLabel(REGISTER_CREDIT_LIMIT_CHECKER_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {REGISTER_CREDIT_LIMIT_DIRECTOR_STEP, getProcessMessageByLabel(REGISTER_CREDIT_LIMIT_DIRECTOR_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {MOTIVATE_REJECTION_STEP, getProcessMessageByLabel(MOTIVATE_REJECTION_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {REJECTION_CREATED_STEP, getProcessMessageByLabel(REJECTION_CREATED_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {DIRECTOR_MOTIVATE_ACCEPT_STEP, getProcessMessageByLabel(DIRECTOR_MOTIVATE_ACCEPT_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {DIRECTOR_MOTIVATE_OVERRULE_STEP, getProcessMessageByLabel(DIRECTOR_MOTIVATE_OVERRULE_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {FINISH_COMPANY_REGISTRATION_STEP, getProcessMessageByLabel(FINISH_COMPANY_REGISTRATION_STEP, true), ProcessTypeDetails.REGISTRATION_COMPANY_CODE},
        {RP_REGISTRATION_PERSON_PROCESS_OWNER_STEP, getProcessMessageByLabel(RP_REGISTRATION_PERSON_PROCESS_OWNER_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_REGISTRATION_PERSON_DETAILS_STEP, getProcessMessageByLabel(RP_REGISTRATION_PERSON_DETAILS_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_INTERVIEWS_STEP, getProcessMessageByLabel(RP_INTERVIEWS_STEP, true), ProcessTypeDetails.INTERVIEW_CODE},
        {RP_EDIT_PERSON_DETAILS_STEP, getProcessMessageByLabel(RP_EDIT_PERSON_DETAILS_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_REJECT_PERSON_STEP, getProcessMessageByLabel(RP_REJECT_PERSON_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_ACCEPT_PERSON_STEP, getProcessMessageByLabel(RP_ACCEPT_PERSON_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_PROSPECT_PERSON_STEP, getProcessMessageByLabel(RP_PROSPECT_PERSON_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_PUBLICATION_STEP, getProcessMessageByLabel(RP_PUBLICATION_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {RP_FINISHED_PERSON_STEP, getProcessMessageByLabel(RP_FINISHED_PERSON_STEP, true), ProcessTypeDetails.REGISTRATION_PERSON_CODE},
        {REGISTER_INTERVIEW_REPORT_STEP, getProcessMessageByLabel(REGISTER_INTERVIEW_REPORT_STEP, true), ProcessTypeDetails.INTERVIEW_CODE},
        {FINISH_INTERVIEW_STEP, getProcessMessageByLabel(FINISH_INTERVIEW_STEP, true), ProcessTypeDetails.INTERVIEW_CODE},
        {ENTER_REQUEST_DETAILS_STEP, getProcessMessageByLabel(ENTER_REQUEST_DETAILS_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},
        {SEARCH_CANDIDATE_STEP, getProcessMessageByLabel(SEARCH_CANDIDATE_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},
        {CLOSE_REQUEST_STEP, getProcessMessageByLabel(CLOSE_REQUEST_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},
        {CANDIDATE_STATUS_STEP, getProcessMessageByLabel(CANDIDATE_STATUS_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},
        {CLOSED_REQUEST_STEP, getProcessMessageByLabel(CLOSED_REQUEST_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},
        {FINISHED_REQUEST_STEP, getProcessMessageByLabel(FINISHED_REQUEST_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},
        {REGISTER_REQUEST_CANDIDATES_STEP, getProcessMessageByLabel(REGISTER_REQUEST_CANDIDATES_STEP, true), ProcessTypeDetails.REGISTRATION_REQUEST_CODE},

        {AA_SEARCH_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_SEARCH_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_ENTER_ASSIGNMENT_AGREEMENT_DETAILS_STEP, getProcessMessageByLabel(AA_ENTER_ASSIGNMENT_AGREEMENT_DETAILS_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_CUSTOMER_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_CUSTOMER_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_DISAGREEMENT_ACCEPT_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_DISAGREEMENT_ACCEPT_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_DISAGREEMENT_REJECT_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_DISAGREEMENT_REJECT_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_SUCCESS_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_SUCCESS_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_FINISH_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_FINISH_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_UNSUCCESSFULLY_FINISH_ASSIGNMENT_AGREEMENT_STEP, getProcessMessageByLabel(AA_UNSUCCESSFULLY_FINISH_ASSIGNMENT_AGREEMENT_STEP, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_CONTRACT_RETURNED_SIGNED, getProcessMessageByLabel(AA_CONTRACT_RETURNED_SIGNED, true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},
        {AA_REJECT_AGREEMENT , getProcessMessageByLabel(AA_REJECT_AGREEMENT , true), ProcessTypeDetails.REGISTRATION_ASSIGNMENT_AGREEMENT_CODE},

        {EC_FIND_PERSON_STEP , getProcessMessageByLabel(EC_FIND_PERSON_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_PASSPORT_CHECK_STEP , getProcessMessageByLabel(EC_PASSPORT_CHECK_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_PASSPORT_REGISTER_DETAILS_STEP , getProcessMessageByLabel(EC_PASSPORT_REGISTER_DETAILS_STEP, true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_CONTRACT_VALUES_MANAGER_STEP, getProcessMessageByLabel(EC_CONTRACT_VALUES_MANAGER_STEP, true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_CONTRACT_DETAILS_STEP, getProcessMessageByLabel(EC_CONTRACT_DETAILS_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_DIRECTOR_APPROVAL_STEP , getProcessMessageByLabel(EC_DIRECTOR_APPROVAL_STEP, true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_APPROVED_STEP, getProcessMessageByLabel(EC_APPROVED_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_CONTRACT_REPORT_DETAILS_STEP, getProcessMessageByLabel(EC_CONTRACT_REPORT_DETAILS_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_CONTRACT_FINAL_REPORT_DETAILS_STEP, getProcessMessageByLabel(EC_CONTRACT_FINAL_REPORT_DETAILS_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_FINAL_APPROVAL_STEP , getProcessMessageByLabel(EC_FINAL_APPROVAL_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_CONTRACT_SIGNED_STEP , getProcessMessageByLabel(EC_CONTRACT_SIGNED_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_FINAL_REJECTION_STEP , getProcessMessageByLabel(EC_FINAL_REJECTION_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_FINAL_REJECTION_CONCEPT_STEP , getProcessMessageByLabel(EC_FINAL_REJECTION_CONCEPT_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_SUCCESFULLY_FINISH_STEP , getProcessMessageByLabel(EC_SUCCESFULLY_FINISH_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},
        {EC_UNSUCCESFULLY_FINISH_STEP , getProcessMessageByLabel(EC_UNSUCCESFULLY_FINISH_STEP , true), ProcessTypeDetails.REGISTRATION_EMPLOYMENT_CONTRACT_CODE},

        {INT_SHEDULE_INTERVIEWS_STEP, getProcessMessageByLabel(INT_SHEDULE_INTERVIEWS_STEP , true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},
        {INT_REJECT_PERSON_STEP, getProcessMessageByLabel(INT_REJECT_PERSON_STEP , true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},
        {INT_ACCEPT_PERSON_STEP, getProcessMessageByLabel(INT_ACCEPT_PERSON_STEP , true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},
        {INT_PROSPECT_PERSON_STEP, getProcessMessageByLabel(INT_PROSPECT_PERSON_STEP , true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},
        {INT_PUBLICATION_STEP, getProcessMessageByLabel(INT_PUBLICATION_STEP , true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},
        {INT_FINISH_SHEDULE_INTERVIEWS_STEP, getProcessMessageByLabel(INT_FINISH_SHEDULE_INTERVIEWS_STEP , true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},
        {REGISTER_INTERVIEW_REPORT_STEP, getProcessMessageByLabel(REGISTER_INTERVIEW_REPORT_STEP, true), ProcessTypeDetails.SHEDULE_INTERVIEW_CODE},

        {RR_FIND_PERSON_STEP, getProcessMessageByLabel(RR_FIND_PERSON_STEP, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_CHECK_PASSPORT, getProcessMessageByLabel(RR_CHECK_PASSPORT,true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_PASSPORT_EDIT, getProcessMessageByLabel(RR_PASSPORT_EDIT,true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_APPROVAL_DOCUMENT, getProcessMessageByLabel(RR_APPROVAL_DOCUMENT,true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_SELECT_RESUME_FROM_FILE, getProcessMessageByLabel(RR_SELECT_RESUME_FROM_FILE, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_LOAD_RESUME_FROM_FILE, getProcessMessageByLabel(RR_LOAD_RESUME_FROM_FILE, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_REGISTER_PERSON_DETAILS_STEP, getProcessMessageByLabel(RR_REGISTER_PERSON_DETAILS_STEP, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_REGISTER_EDUCATION_AND_COURSES_STEP, getProcessMessageByLabel(RR_REGISTER_EDUCATION_AND_COURSES_STEP, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_REGISTER_KNOWLEDGE_MATRIX_STEP, getProcessMessageByLabel(RR_REGISTER_KNOWLEDGE_MATRIX_STEP, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_REGISTER_WORK_EXPERIENCE_DETAILS,getProcessMessageByLabel(RR_REGISTER_WORK_EXPERIENCE_DETAILS,true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_REGISTER_SUMMARY_STEP, getProcessMessageByLabel(RR_REGISTER_SUMMARY_STEP,true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_PRINT_RESUME, getProcessMessageByLabel(RR_PRINT_RESUME,true), ProcessTypeDetails.REGISTRATION_RESUME_CODE},
        {RR_FINISH_STEP, getProcessMessageByLabel(RR_FINISH_STEP, true), ProcessTypeDetails.REGISTRATION_RESUME_CODE}

        };
       //####################################################################################################
       public static List<SelectItem> getStepsList() {
          List<String> stepsString=new ArrayList<String>();
          for(String[] step:STEPS)
          {
            if(!step[0].equals(SELECT_VALUE_LABEL))stepsString.add(step[1]);
          }
          if(!Helper.isEmpty(stepsString)) Collections.sort(stepsString);
          List<SelectItem> sortStepsList=new ArrayList<SelectItem>();
          sortStepsList.add(new SelectItem(SELECT_VALUE_LABEL, SELECT_VALUE_STEP));
          for(String value:stepsString)
          {
            sortStepsList.add(new SelectItem(getStepLabelByValue(value), value));
          }
          return sortStepsList;
        }//####################################################################################################
        private static List<SelectItem> getSortStepsList(String codeProcess)
        {
            if (Helper.isEmpty(codeProcess))
            {
                return getStepsList();
            }
            List<String> stepsString=new ArrayList<String>();
            for( String[] step:STEPS )
            {
              if( codeProcess.equals(step[2]) )
              {
                  stepsString.add(step[1]);
              }
            }
          if(!Helper.isEmpty(stepsString)) Collections.sort(stepsString);
          List<SelectItem> sortStepsList=new ArrayList<SelectItem>();
          for(String value:stepsString)
          {
            sortStepsList.add(new SelectItem(getStepLabelByValue(value), value));
          }
          return sortStepsList;
        }//####################################################################################################
        public static List<SelectItem> getStepsListFromCodeProcess(String codeProcess)
        {
            List<SelectItem> stepsList=new ArrayList<SelectItem>();
            if(codeProcess!=null && !codeProcess.equals(SELECT_VALUE_LABEL) )
                stepsList.add(new SelectItem(SELECT_VALUE_LABEL, SELECT_VALUE_STEP));
            stepsList.addAll(getSortStepsList(codeProcess));
          return stepsList;
        }//####################################################################################################
        public static String getCodeProcessFromCodeStep(String codeStep)
        {
            if (codeStep == null)
            {
                return "";
            }
            for( String[] step:STEPS )
            {
              if( codeStep.equals(step[0]) )
              {
                 return step[2];
              }
            }
          return "";
        }//####################################################################################################
        public static String getStepLabelByValue(String value)
        {
          if(value==null)return "";
          for( String[] step:STEPS )
          {
             if(step[1].equals(value))return step[0];
          }
         return "";
        }//####################################################################################################
        public static String getStepValueByLabel(String label)
        {
          if(label==null)return "";
          for( String[] step:STEPS )
          {
             if(step[0].equals(label))return step[1];
          }
         return "";
        }
        //####################################################################################################
        public static String getProcessMessageByLabel(String label, boolean details)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(INT_REJECT_PERSON_STEP))
            {
                  return "Reject";
            }
            if (label.equals(INT_ACCEPT_PERSON_STEP))
            {
                  return "Accept";
            }
            if (label.equals(INT_PROSPECT_PERSON_STEP))
            {
                  return "Prospect";
            }
            if (label.equals(INT_PUBLICATION_STEP))
            {
                  return "Publication";
            }
            if (label.equals(INT_FINISH_SHEDULE_INTERVIEWS_STEP))
            {
                  return "Finished";
            }
            if (label.equals(INT_SHEDULE_INTERVIEWS_STEP))
            {
                return "Schedule interview(s)";
            }
            if (label.equals(INT_FIND_PERSON_STEP))
            {
                return !details?FacesHelper.getBundleMessage("interview_search_psn"):"Search person for interview";
            }
            if (label.equals(ENTER_COMPANY_DETAILS_STEP))
            {
                return !details?FacesHelper.getBundleMessage("process_registration"):"Register company details";
            }
            if(label.equals(RP_EDIT_PERSON_DETAILS_STEP))
            {
               return FacesHelper.getBundleMessage("intw_step_editpersdet");
            }
            if (label.equals(CREDIT_CHECK_STEP))
            {
                return FacesHelper.getBundleMessage("process_creditcheck");
            }
            if (label.equals(PASSED_CHECK_STEP))
            {
                return FacesHelper.getBundleMessage("process_passedcheck");
            }
            if (label.equals(REGISTER_CREDIT_LIMIT_CHECKER_STEP))
            {
                return FacesHelper.getBundleMessage("process_registercreditlimit");
            }
            if (label.equals(REGISTER_CREDIT_LIMIT_DIRECTOR_STEP))
            {
                return FacesHelper.getBundleMessage("process_registercreditlimit_dir");
            }
            if (label.equals(MOTIVATE_REJECTION_STEP))
            {
                return FacesHelper.getBundleMessage("process_motivaterejection");
            }
            if (label.equals(REJECTION_CREATED_STEP))
            {
                return FacesHelper.getBundleMessage("process_rejectioncreated");
            }
            if (label.equals(DIRECTOR_MOTIVATE_ACCEPT_STEP))
            {
                return !details?FacesHelper.getBundleMessage("process_directoraccept"):FacesHelper.getBundleMessage("process_directoraccept")+" :credi check";
            }
            if (label.equals(DIRECTOR_MOTIVATE_OVERRULE_STEP))
            {
                return FacesHelper.getBundleMessage("process_directoroverrule");
            }
            if (label.equals(FINISH_COMPANY_REGISTRATION_STEP))
            {
                return !details?FacesHelper.getBundleMessage("process_finished"):"Register company process  finished";
            }
            if (label.equals(RP_REGISTRATION_PERSON_PROCESS_OWNER_STEP))
            {
                return FacesHelper.getBundleMessage("process_owner_registration");
            }
            if (label.equals(RP_REGISTRATION_PERSON_DETAILS_STEP))
            {
                return !details?FacesHelper.getBundleMessage("process_persondetails_registration"):"Register person details";
            }
            if (label.equals(RP_INTERVIEWS_STEP))
            {
                return FacesHelper.getBundleMessage("process_interviews");
            }
            if (label.equals(RP_REJECT_PERSON_STEP))
            {
                return FacesHelper.getBundleMessage("process_reject_person");
            }
            if (label.equals(RP_ACCEPT_PERSON_STEP))
            {
                return FacesHelper.getBundleMessage("process_accept_person");
            }
            if (label.equals(RP_PROSPECT_PERSON_STEP))
            {
                return FacesHelper.getBundleMessage("process_prospect_person");
            }
            if (label.equals(RP_PUBLICATION_STEP))
            {
                return FacesHelper.getBundleMessage("process_person_publication");
            }
            if (label.equals(RP_FINISHED_PERSON_STEP))
            {
                return !details?FacesHelper.getBundleMessage("process_finished"):"Register person process finished";
            }/////////////////////////////////////////////////////////
            if (label.equals(AA_ENTER_ASSIGNMENT_AGREEMENT_DETAILS_STEP))
            {
                return !details?FacesHelper.getBundleMessage("reg_ass_agr_step_reg_details"):"Register assignment agreement details";
            }
            if (label.equals(AA_CUSTOMER_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("reg_ass_agr_step_cust_aggr");
            }
            if (label.equals(AA_DISAGREEMENT_ACCEPT_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("reg_ass_agr_step_disaggr_accept");
            }
            if (label.equals(AA_DISAGREEMENT_REJECT_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("reg_ass_agr_step_disaggr_reject");
            }
            if (label.equals(AA_SUCCESS_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("reg_ass_agr_step_success");
            }
            if (label.equals(AA_CONTRACT_RETURNED_SIGNED))
            {
                return FacesHelper.getBundleMessage("reg_ass_agr_step_signed");
            }

            if (label.equals(AA_REJECT_AGREEMENT))
            {
                return FacesHelper.getBundleMessage("reg_ass_agr_step_reject");
            }
            if (label.equals(AA_UNSUCCESSFULLY_FINISH_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("unsuccess_aa");
            }
            if (label.equals(REGISTER_INTERVIEW_REPORT_STEP))
            {
                return FacesHelper.getBundleMessage("process_interview_reg_report");
            }
            if (label.equals(ENTER_REQUEST_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("process_request_details_registration");
            }

            if (label.equals(FINISH_INTERVIEW_STEP))
            {
                return !details?FacesHelper.getBundleMessage("interview_step_finish"):"Finished";
            }
            if (label.equals(SEARCH_CANDIDATE_STEP))
            {
                return FacesHelper.getBundleMessage("process_request_search_candidate");
            }
            if (label.equals(REGISTER_REQUEST_CANDIDATES_STEP))
            {
                return FacesHelper.getBundleMessage("process_request_register_request_candidates");
            }
            if (label.equals(CLOSE_REQUEST_STEP))
            {
                return FacesHelper.getBundleMessage("process_request_close_request");
            }
            if (label.equals(CLOSED_REQUEST_STEP))
            {
                return FacesHelper.getBundleMessage("process_request_request_closed");
            }
            if (label.equals(CANDIDATE_STATUS_STEP))
            {
                return FacesHelper.getBundleMessage("process_request_request_candidate_status");
            }
            //ass agr
            if (label.equals(AA_SEARCH_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("process_search_aa");
            }
            if (label.equals(AA_FINISH_ASSIGNMENT_AGREEMENT_STEP))
            {
                return FacesHelper.getBundleMessage("finish_agr");
            }

            //added by ""
            if (label.equals(EC_FIND_PERSON_STEP))
            {
                return FacesHelper.getBundleMessage("find_pers");
            }
            if (label.equals(StepDetails.EC_PASSPORT_CHECK_STEP))
            {
                return FacesHelper.getBundleMessage("step_psp");
            }
            if (label.equals(StepDetails.EC_PASSPORT_REGISTER_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("step_psp_register");
            }
            if (label.equals(StepDetails.EC_CONTRACT_REPORT_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("edit_report");
            }
            if (label.equals(StepDetails.EC_CONTRACT_FINAL_REPORT_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("edit_contract");
            }
            if (label.equals(StepDetails.EC_DIRECTOR_APPROVAL_STEP))
            {
                return FacesHelper.getBundleMessage("step_dir_appr");
            }
            if (label.equals(StepDetails.EC_APPROVED_STEP))
            {
                return FacesHelper.getBundleMessage("step_approved");
            }
            if (label.equals(StepDetails.EC_FINAL_REJECTION_STEP))
            {
                return FacesHelper.getBundleMessage("step_cntr_reject");
            }
            if (label.equals(StepDetails.EC_FINAL_REJECTION_CONCEPT_STEP))
            {
                return FacesHelper.getBundleMessage("step_concept_fin_rej");
            }

            if (label.equals(StepDetails.EC_CONTRACT_VALUES_MANAGER_STEP))
            {
                return FacesHelper.getBundleMessage("man_contr_registration");
            }
            if (label.equals(StepDetails.EC_CONTRACT_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("emp_contr_registration");
            }
            if (label.equals(StepDetails.EC_CONTRACT_REPORT_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("edit_report");
            }

            if (label.equals(StepDetails.EC_CONTRACT_SIGNED_STEP))
            {
                return FacesHelper.getBundleMessage("step_ctr_sign");
            }
            if (label.equals(StepDetails.EC_FINAL_APPROVAL_STEP))
            {
                return FacesHelper.getBundleMessage("step_fin_approval");
            }
            if (label.equals(StepDetails.EC_SUCCESFULLY_FINISH_STEP))
            {
                return FacesHelper.getBundleMessage("emp_contr_finished");
            }
            if (label.equals(StepDetails.FINISHED_REQUEST_STEP))
            {
                return "Finished register request process";
            }
            if (label.equals(RR_FIND_PERSON_STEP))
            {
                return FacesHelper.getBundleMessage("resume_search_psn");
            }
            if (label.equals(RR_CHECK_PASSPORT))
            {
                return FacesHelper.getBundleMessage("step_check_psp");
            }
            if (label.equals(RR_PASSPORT_EDIT))
            {
                return FacesHelper.getBundleMessage("resume_ed_psp");
            }
            if (label.equals(RR_APPROVAL_DOCUMENT))
            {
                return FacesHelper.getBundleMessage("approval_doc");
            }
            if (label.equals(RR_SELECT_RESUME_FROM_FILE))
            {
                return FacesHelper.getBundleMessage("sel_res");
            }
            if (label.equals(RR_LOAD_RESUME_FROM_FILE))
            {
                return FacesHelper.getBundleMessage("load_res");
            }
            if (label.equals(RR_REGISTER_EDUCATION_AND_COURSES_STEP))
            {
                return FacesHelper.getBundleMessage("reg_ed_crs");
            }
            if (label.equals(RR_REGISTER_PERSON_DETAILS_STEP))
            {
                return FacesHelper.getBundleMessage("reg_resume");
            }
            if (label.equals(RR_REGISTER_KNOWLEDGE_MATRIX_STEP))
            {
                return FacesHelper.getBundleMessage("reg_kn_m");
            }
            if (label.equals(RR_REGISTER_WORK_EXPERIENCE_DETAILS))
            {
                return FacesHelper.getBundleMessage("res_work_exp");
            }

            if (label.equals(RR_REGISTER_SUMMARY_STEP))
            {
                return FacesHelper.getBundleMessage("reg_sum");
            }
            if (label.equals(RR_PRINT_RESUME))
            {
                return FacesHelper.getBundleMessage("reg_pr");
            }
            if (label.equals(RR_FINISH_STEP))
            {
                return "Finishing of registration of resume";
            }

            return "";
        }
        public static String getProcessMessageByLabel(String label)
        {
          return getProcessMessageByLabel(label, false);
        }
    }

    public static class Navigation
    {
        public static final String REGISTER_DETAILS = "registerDetails";
        public static final String CREATION_STANDARD_ACTION = "creationStandardAction";
        public static final String REGISTER_CATEGORY = "creationCategories";
        public static final String REGISTER_DETAILS_ADMIN = "registerDetailsAdmin";
        public static final String HOME = "home";
        public static final String INDEX = "index";
        public static final String CATALOG = "catalog";
        public static final String APPLICATION_ERRORS_VIEW = "searchApplicationErrors";
        public static final String BEGIN_CREDIT_CHECK = "creditCheck";
        public static final String LOGIN = "login";
        public static final String REGISTER_PERSON_DETAILS = "registerPersonDetails";
        public static final String REGISTER_PERSON_PROCESS_OWNER = "registerPersonProcessOwner";
        public static final String APPOINTMENT_INTERVIEWERS = "appointmentInterviewers";
        public static final String INTERVIEW = "interview";
        public static final String LOOK_REPORT = "lookReport";

        public static final String LOOK_PERSON = "lookPerson";
        public static final String SEARCH_COMPANY_ADDRESSES="searchCompanyAddresses";
        public static final String LOOK_OWNREG_PESON_ALL_DETAILS = "lookOwnRegPesonAllDetails";
        public static final String LOOK_ACC_PESON_ALL_DETAILS = "lookAccPesonAllDetails";
        public static final String LOOK_INT_PESON_ALL_DETAILS = "lookIntPesonAllDetails";


        public static final String REGISTER_PERSON_ACTIONS = "registerPersonActions";
        public static final String REGISTER_PERSON_PUBLISH = "registerPersonPublish";
        public static final String REGISTER_REQUEST_FIND_COMPANY = "registerRequestFindCompany";
        public static final String REGISTER_REQUEST_DETAILS = "registerRequestDetails";
        public static final String REGISTER_LOOK_REQUEST_DETAILS = "lookRequestDetails";
        public static final String REGISTER_REQUEST_FIND_POSITION = "registerRequestFindPosition";
        public static final String REGISTER_PERSON_FIND_POSITION = "registerPersonFindPosition";
        public static final String REGISTER_REQUEST_SEARCH_CANDIDATE = "registerRequestSearchCandidate";
        public static final String REGISTER_REQUEST_CLOSE_REQUEST = "closeRequest";
        public static final String REGISTER_REQUEST_CANDIDATE_STATUS = "candidateStatus";

        public static final String REGISTER_ASSIGNMENT_AGREEMENT_DETAILS = "registerAssignmentAgreementDetails";
        public static final String CUSTOMER_ASSIGNMENT_AGREEMENT = "customerAssignmentAgreement";
        public static final String SUCCESS_ASSIGNMENT_AGREEMENT = "successAssignmentAgreement";
        public static final String REJECT_ASSIGNMENT_AGREEMENT = "rejectAssignmentAgreement";
        //21.05.87
        public static final String REGISTER_INNER_PERSON_DETAILS = "creationInnerPerson";
        public static final String REGISTER_PASSPORT_PERSON_DETAILS = "passportEdit";
        public static final String REGISTER_WORK_EXPERIENCE_DETAILS = "inputResumeWorkExperience";
        public static final String REGISTER_POSITION = "creationPositions";
        public static final String FIND_POSITION = "findPositions";
        public static final String FIND_CATEGORY = "findCategories";
        public static final String FIND_POSITION_FROM_PERSON = "findPositionsFromPerson";
        public static final String FIND_INNER_PERSON = "findInnerPerson";
        public static final String REGISTER_ROLE = "creationRoles";
        public static final String REGISTER_DOCUMENTS = "creationDocuments";
        public static final String FIND_ROLE = "findRoles";
        public static final String FIND_LOCATION = "findLocation";
        public static final String CREATION_LOCATION = "creationLocation";
        public static final String FIND_DOCUMENTS = "findDocuments";
        public static final String FIND_COMPANY = "findCompany";
        public static final String FIND_STANDARDACTION = "findStandardAction";

        public static final String LOOK_COMPANY_DETAILS = "lookCompanyDetails";
        public static final String FIND_COMPANY_SOURCE_PAGE = "find";
        public static final String REGISTER_REQUEST_SOURCE_PAGE = "register";
        public static final String FIND_REQUEST_LOCATION = "findRequestLocation";
        public static final String FIND_REQUEST_CATEGORY = "findRequestCategory";
        public static final String FINDER_CATEGORY = "finderCategory";
        public static final String CREATION_CATEGORY = "creationCategories";


        public static final String SEARCH_CATEGORY_FROM_POSITION_LABEL = "P";
        public static final String SEARCH_CATEGORY_FROM_MENU_LABEL = "M";
        public static final String CLOSE_PERSON_ACTION = "closePersonAction";

        public static final String VIEW_ALL_PROCESSES = "viewAllProcesses";
        //added by ""
        public static final String STEP_SEARCH_ASSIGNMENT_AGREEMENT = "searchAssignmentAgreement";
        public static final String STEP_ADMIN_SEARCH_ASSIGNMENT_AGREEMENT = "finderAssignmentAgreement";

        public static final String EC_START_EMPLOYMENT_CONTRACT = "registerEmploymentContract";
        public static final String EC_FIND_PERSON_STEP = "searchPerson";
        public static final String EC_FIND_COMPANY_STEP = "searchCompany";
        public static final String EC_PASSPORT_CHECK_STEP = "passportPerson";
        public static final String EC_CONTRACT_VALUES_MANAGER_STEP = "contractApproval";
        public static final String EC_CONTRACT_DETAILS_STEP = "contractApproval";
        public static final String EC_DIRECTOR_APPROVAL_STEP = "contractApproval";
        public static final String EC_FINAL_REJECTION_STEP = "contractApproval";
        public static final String EC_SUCCESFULLY_FINISH_STEP = "home";
        public static final String EC_UNSUCCESFULLY_FINISH_STEP = "home";
        public static final String SEARCH_CONTACT_PERSONS = "searchContactPersons";
        public static final String EC_FINAL_APPROVAL_STEP="contractApproval";
        public static final String ADMIN_CONTRACT_SEARCH="searchEmploymentContract";
        public static final String ADMIN_CONTRACT_EDIT="editContract";
        public static final String ADMIN_AGREEMENT_EDIT="editAgreement";

        public static final String INT_FIND_PERSON_STEP="searchPerson";
        public static final String INT_SHEDULE_INTERVIEWS_STEP="appointmentInterviewers";
        public static final String EDIT_AND_VIEW_PERSON_CONTACT="editAndViewPersonContact";
        public static final String EDIT_AND_VIEW_COMPANY_CONTACT="editAndViewCompanyContact";
        public static final String VIEW_ALL_PERSON_CONTACTS="viewAllPersonContactManagement";
        public static final String VIEW_ALL_COMPANY_CONTACTS="viewAllCompanyContactManagement";
        public static final String VIEW_ALL_EMPLOYMENT_CONTRACT_CHANGES_HISTORY="viewAllEmploymentContractHistory";
        public static final String VIEW_EC_CHANGES_HISTORY="viewAllEmploymentContractHistory";
        public static final String VIEW_EC_CHANGES_HISTORY_FOR_ADMIN ="viewEmploymentContractHistoryForAdmin";
        public static final String VIEW_CHANGES_HISTORY_FOR_PERSON ="viewChangesHistoryForPerson";
        public static final String VIEW_CHANGES_HISTORY_FOR_COMPANY ="viewChangesHistoryForCompany";
        public static final String VIEW_COMPANY_ADDRESS_CHANGES_HISTORY ="viewCompanyAddressChangesHistory";
        public static final String VIEW_PERSON_ADDRESS_CHANGES_HISTORY ="viewPersonAddressChangesHistory";

        public static final String RR_REGISTERING_RESUME_STEP="registerResumeDetails";

        public static final String COMPANY_ADDRESS_INPUT_END_DATE="registerEndDateCompanyAddress";

    }

   public static class ProcessTypeDetails
    {
        public static final String REGISTRATION_COMPANY_CODE = "RGCP";
        public static final String REGISTRATION_PERSON_CODE = "RGPR";
        public static final String INTERVIEW_CODE = "INTW";
        public static final String SHEDULE_INTERVIEW_CODE="SHIP";
        public static final String REGISTRATION_REQUEST_CODE = "RGRQ";
        public static final String REGISTRATION_ASSIGNMENT_AGREEMENT_CODE = "RGAA";
        public static final String REGISTRATION_EMPLOYMENT_CONTRACT_CODE = "RGEC";
        public static final String REGISTRATION_RESUME_CODE = "RGRS";


    }

    public static class CategoriesDetails
    {
        public static final String INF_LABEL = "I";
        public static final String INF_VALUE = "Informatie Technologie";
        public static final String TECH_LABEL = "T";
        public static final String TECH_VALUE = "Administratie & Techniek";
        public static final String TEL_LABEL = "D";
        public static final String TEL_VALUE = "Telecom, Energie & Infra";
        public static final String MAN_LABEL = "M";
        public static final String MAN_VALUE = "Management & Staf";
        public static final String SEL_LABEL = "U";
        public static final String SEL_VALUE = "Undefined";

        public static String getCategoriesValueByLabel(String label)
        {
            if (label == null || label.equals(SEL_LABEL))
            {
                return "";
            }
            if (label.equals(INF_LABEL))
            {
                return INF_VALUE;
            }
            if (label.equals(TECH_LABEL))
            {
                return TECH_VALUE;
            }
            if (label.equals(MAN_LABEL))
            {
                return MAN_VALUE;
            }
            if (label.equals(TEL_LABEL))
            {
                return TEL_VALUE;
            }
            return "";
        }
    }

    public static class AssignmentAgreementDetails
    {
        public static final String PAYMENT_TERM_DAYS_LABEL = "D";
        public static final String PAYMENT_TERM_DAYS_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_payment_term_days_d");
        public static final String PAYMENT_TERM_WEEKS_LABEL = "W";
        public static final String PAYMENT_TERM_WEEKS_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_payment_term_weeks_d");
        public static final String PAYMENT_TERM_MONTHS_LABEL = "M";
        public static final String PAYMENT_TERM_MONTHS_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_payment_term_months_d");
        public static final String PAYMENT_TERM_UNDEFINED_LABEL = "U";
        public static final String PAYMENT_TERM_UNDEFINED_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_payment_term_undefined");

        public static final String RATE_CURRENCY_DOLLAR_LABEL = "D";
        public static final String RATE_CURRENCY_DOLLAR_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_currency_dollar");
        public static final String RATE_CURRENCY_EURO_LABEL = "E";
        public static final String RATE_CURRENCY_EURO_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_currency_euro");
        public static final String RATE_CURRENCY_UNDEFINED_LABEL = "U";
        public static final String RATE_CURRENCY_UNDEFINED_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_currency_undefined");

        public static final String RATE_UNIT_HOUR_LABEL = "H";
        public static final String RATE_UNIT_HOUR_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_unit_per_hour_d");
        public static final String RATE_UNIT_MOUNTH_LABEL = "M";
        public static final String RATE_UNIT_MOUNTH_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_unit_per_mounth_d");
        public static final String RATE_UNIT_PIECE_LABEL = "P";
        public static final String RATE_UNIT_PIECE_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_unit_piece_d");
        public static final String RATE_UNIT_UNDEFINED_LABEL = "U";
        public static final String RATE_UNIT_UNDEFINED_VALUE = FacesHelper.getBundleMessage("reg_ass_agr_rate_unit_undefined");

        public static String getPaymentTermValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(PAYMENT_TERM_DAYS_LABEL))
            {
                return PAYMENT_TERM_DAYS_VALUE;
            }
            else if (label.equals(PAYMENT_TERM_WEEKS_LABEL))
            {
                return PAYMENT_TERM_WEEKS_VALUE;
            }
            else if (label.equals(PAYMENT_TERM_MONTHS_LABEL))
            {
                return PAYMENT_TERM_MONTHS_VALUE;
            }
            else if (label.equals(PAYMENT_TERM_UNDEFINED_LABEL))
            {
                return PAYMENT_TERM_UNDEFINED_VALUE;
            }
            else
            {
                return "";
            }
        }

        public static String getRateCurrencyValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }

            label = label.trim();
            if (label.equals(RATE_CURRENCY_DOLLAR_LABEL))
            {
                return RATE_CURRENCY_DOLLAR_VALUE;
            }
            else if (label.equals(RATE_CURRENCY_EURO_LABEL))
            {
                return RATE_CURRENCY_EURO_VALUE;
            }
            else if (label.equals(RATE_CURRENCY_UNDEFINED_LABEL))
            {
                return RATE_CURRENCY_UNDEFINED_VALUE;
            }
            else
            {
                return "";
            }
        }

        public static String getRateUnitValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(RATE_UNIT_HOUR_LABEL))
            {
                return RATE_UNIT_HOUR_VALUE;
            }
            else if (label.equals(RATE_UNIT_MOUNTH_LABEL))
            {
                return RATE_UNIT_MOUNTH_VALUE;
            }
            else if (label.equals(RATE_UNIT_PIECE_LABEL))
            {
                return RATE_UNIT_PIECE_VALUE;
            }
            else if (label.equals(RATE_UNIT_PIECE_LABEL))
            {
                return RATE_UNIT_PIECE_VALUE;
            }
            else
            {
                return "";
            }
        }

        //added by ""
        //statuses
        // Created, Disagreement Reject, Disagreement Accept, Rejected and Signed;
        public static final String CREATED_LABEL = "C";
        public static final String CREATED_VALUE = "Created";
        public static final String CREATED_MOTIVATION = FacesHelper.getBundleMessage("cr_aa_motiv");
        public static final String DISAGREEMENT_REJECT_LABEL = "R";
        public static final String DISAGREEMENT_REJECT_VALUE = "Disagreement Reject";
        public static final String DISAGREEMENT_ACCEPT_LABEL = "A";
        public static final String DISAGREEMENT_ACCEPT_VALUE = "Disagreement Accept";
        public static final String REJECTED_LABEL = "J";
        public static final String REJECTED_VALUE = "Rejected";
        public static final String SIGNED_LABEL = "S";
        public static final String SIGNED_VALUE = "Signed";

        public static String getStatusValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(CREATED_LABEL))
            {
                return CREATED_VALUE;
            }
            if (label.equals(DISAGREEMENT_REJECT_LABEL))
            {
                return DISAGREEMENT_REJECT_VALUE;
            }
            if (label.equals(DISAGREEMENT_ACCEPT_LABEL))
            {
                return DISAGREEMENT_ACCEPT_VALUE;
            }
            if (label.equals(REJECTED_LABEL))
            {
                return REJECTED_VALUE;
            }
            if (label.equals(SIGNED_LABEL))
            {
                return SIGNED_VALUE;
            }
            return "";
        }

        public static String getStatusLabelByValue(String value)
        {
            if (value == null)
            {
                return "";
            }
            if (value.equals(CREATED_VALUE))
            {
                return CREATED_LABEL;
            }
            if (value.equals(DISAGREEMENT_REJECT_VALUE))
            {
                return DISAGREEMENT_REJECT_LABEL;
            }
            if (value.equals(DISAGREEMENT_ACCEPT_VALUE))
            {
                return DISAGREEMENT_ACCEPT_LABEL;
            }
            if (value.equals(REJECTED_VALUE))
            {
                return REJECTED_LABEL;
            }
            if (value.equals(SIGNED_VALUE))
            {
                return SIGNED_LABEL;
            }
            return "";
        }
        public static final String SELECT_VALUE_LABEL = "O";
        public static final String SELECT_VALUE = FacesHelper.getBundleMessage("select_value");
        public static final String N_A_LABEL="N";
        public static final String N_A_VALUE="N/A";
        public static final String TRIPTICOM_LABEL="T";
        public static final String TRIPTICOM_VALUE="By Tripticom";
        public static final String TRIPTICOM_VALUE_D="Tripticom";
        public static final String CUSTOMER_LABEL="C";
        public static final String CUSTOMER_VALUE="By customer";
        public static final String CUSTOMER_VALUE_D="Klant";

        public static String CHOOSE[][] = {
                {SELECT_VALUE_LABEL, SELECT_VALUE},
                {N_A_LABEL, N_A_VALUE},
                {TRIPTICOM_LABEL, TRIPTICOM_VALUE},
                {CUSTOMER_LABEL, CUSTOMER_VALUE}
        };


        public static String CHOOSE_H[][] = {
                {SELECT_VALUE_LABEL, SELECT_VALUE},
                {N_A_LABEL, N_A_VALUE},
                {TRIPTICOM_LABEL, TRIPTICOM_VALUE_D},
                {CUSTOMER_LABEL, CUSTOMER_VALUE_D}
        };

        //Laptop
        public static String LAPTOP_UNDEFINED_LABEL = "O";
        public static String LAPTOP_NOT_ASSIGNED_LABEL = "N";
        public static String LAPTOP_TRIPTICOM_LABEL = "T";
        public static String LAPTOP_CLIENT_LABEL = "C";
        public static String LAPTOP_UNDEFINED_VALUE = "Select value";
        public static String LAPTOP_NOT_ASSIGNED_VALUE = "N/A";
        public static String LAPTOP_TRIPTICOM_VALUE = "Tripticom";
        public static String LAPTOP_CLIENT_VALUE = "Klant";

        private static List<SelectItem> laptopItems = new ArrayList<SelectItem>();

        public static List<SelectItem> getLaptopSelectItems()
        {
            if (laptopItems == null || laptopItems.size() == 0)
            {
                laptopItems.add(new SelectItem(LAPTOP_UNDEFINED_LABEL, LAPTOP_UNDEFINED_VALUE));
                laptopItems.add(new SelectItem(LAPTOP_NOT_ASSIGNED_LABEL, LAPTOP_NOT_ASSIGNED_VALUE));
                laptopItems.add(new SelectItem(LAPTOP_TRIPTICOM_LABEL, LAPTOP_TRIPTICOM_VALUE));
                laptopItems.add(new SelectItem(LAPTOP_CLIENT_LABEL, LAPTOP_CLIENT_VALUE));
            }
            return laptopItems;
        }

        public static String getLaptopValueByLabel(String label)
        {
            if(label == null)
            {
                return "";
            }
            if (label.equals(LAPTOP_TRIPTICOM_LABEL))
            {
                return LAPTOP_TRIPTICOM_VALUE;
            }
            if (label.equals(LAPTOP_CLIENT_LABEL))
            {
                return LAPTOP_CLIENT_VALUE;
            }
            if (label.equals(LAPTOP_NOT_ASSIGNED_LABEL))
            {
                return LAPTOP_NOT_ASSIGNED_VALUE;
            }
            else
            {
                return LAPTOP_UNDEFINED_VALUE;
            }
        }

        //Travel Expenses
        public static String TRAVEL_EXPENSES_UNDEFINED_LABEL = "U";
        public static String TRAVEL_EXPENSES_INCLUDED_LABEL = "I";
        public static String TRAVEL_EXPENSES_BASIS_LABEL = "B";
        public static String TRAVEL_EXPENSES_019_EURO_PER_KM_LABEL = "E";

        public static String TRAVEL_EXPENSES_UNDEFINED_VALUE = FacesHelper.getBundleMessage("travel_expenses_undefined");
        public static String TRAVEL_EXPENSES_INCLUDED_VALUE = FacesHelper.getBundleMessage("travel_expenses_included");
        public static String TRAVEL_EXPENSES_BASIS_VALUE = FacesHelper.getBundleMessage("travel_expenses_basis");
        public static String TRAVEL_EXPENSES_019_EURO_PER_KM_VALUE = FacesHelper.getBundleMessage("travel_expenses_019_euro_per_km");


        private static List<SelectItem> travelExpensesSelectItems = new ArrayList<SelectItem>();

        public static List<SelectItem> getTravelexpensesSelectItems()
        {
            if (travelExpensesSelectItems == null || travelExpensesSelectItems.size() == 0)
            {
                travelExpensesSelectItems.add(new SelectItem(TRAVEL_EXPENSES_UNDEFINED_LABEL, TRAVEL_EXPENSES_UNDEFINED_VALUE));
                travelExpensesSelectItems.add(new SelectItem(TRAVEL_EXPENSES_INCLUDED_LABEL, TRAVEL_EXPENSES_INCLUDED_VALUE));
                travelExpensesSelectItems.add(new SelectItem(TRAVEL_EXPENSES_BASIS_LABEL, TRAVEL_EXPENSES_BASIS_VALUE));
                travelExpensesSelectItems.add(new SelectItem(TRAVEL_EXPENSES_019_EURO_PER_KM_LABEL, TRAVEL_EXPENSES_019_EURO_PER_KM_VALUE));
            }
            return travelExpensesSelectItems;
        }

        public static String getTravelExpensesValueByLabel(String label)
        {
            if (label== null)
            {
                return "";
            }
            if (label.equals(TRAVEL_EXPENSES_INCLUDED_LABEL))
            {
                return TRAVEL_EXPENSES_INCLUDED_VALUE;
            }
            if (label.equals(TRAVEL_EXPENSES_BASIS_LABEL))
            {
                return TRAVEL_EXPENSES_BASIS_VALUE;
            }
            if (label.equals(TRAVEL_EXPENSES_019_EURO_PER_KM_LABEL))
            {
                return TRAVEL_EXPENSES_019_EURO_PER_KM_VALUE;
            }
            else
            {
                return TRAVEL_EXPENSES_UNDEFINED_VALUE;
            }
        }


        public static List<SelectItem> getChooseList()
        {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for (String[] obj : CHOOSE_H)
            {
                list.add(new SelectItem(obj[0], obj[1]));
            }
            return list;
        }

        public static String getChooseValueByLabel(String label)
        {
            for (String[] obj : CHOOSE_H)
            {
                if (obj[0].equals(label))
                {
                    return obj[1];
                }
            }
            return "";
        }

        public static String getChooseLabelByValue(String value)
        {
            for (String[] obj : CHOOSE_H)
            {
                if (obj[1].equals(value))
                {
                    return obj[0];
                }
            }
            return "";
        }

        public static String NOTICE_PERIOD_UNDEFNED_LABEL = "0";
        public static String NOTICE_PERIOD_UNDEFNED_VALUE = FacesHelper.getBundleMessage("select_value");
        public static String NOTICE_PERIOD_WEEK_LABEL = "W";
        public static String NOTICE_PERIOD_WEEK_VALUE = FacesHelper.getBundleMessage("cal_week_d");
        public static String NOTICE_PERIOD_WEEK_SINGULAR_VALUE = FacesHelper.getBundleMessage("singular_week");
        public static String NOTICE_PERIOD_WEEK_PLURAL_VALUE = FacesHelper.getBundleMessage("plural_week");
        public static String NOTICE_PERIOD_MONTH_LABEL = "M";
        public static String NOTICE_PERIOD_MONTH_SINGULAR_VALUE = FacesHelper.getBundleMessage("singular_month");
        public static String NOTICE_PERIOD_MONTH_PLURAL_VALUE = FacesHelper.getBundleMessage("plural_month");
        public static String NOTICE_PERIOD_MONTH_VALUE = FacesHelper.getBundleMessage("cal_month_d");
        public static Long  NOTICE_PERIOD_SINGULAR_LABEL = 1L;


        public static String NOTICE_PERIOD[][] = {
                {NOTICE_PERIOD_UNDEFNED_LABEL, NOTICE_PERIOD_UNDEFNED_VALUE},
                {NOTICE_PERIOD_WEEK_LABEL, NOTICE_PERIOD_WEEK_VALUE},
                {NOTICE_PERIOD_MONTH_LABEL, NOTICE_PERIOD_MONTH_VALUE},
        };


        public static List<SelectItem> getNoticePeriodList()
        {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for (String[] obj : NOTICE_PERIOD)
            {
                list.add(new SelectItem(obj[0], obj[1]));
            }
            return list;
        }

        public static String getNoticePeriodValueByLabel(String label)
        {
            for (String[] obj : NOTICE_PERIOD)
            {
                if (obj[0].equals(label))
                {
                    return obj[1];
                }
            }
            return "";
        }

        public static String getNoticePeriodLabelByValue(String value)
        {
            for (String[] obj : NOTICE_PERIOD)
            {
                if (obj[1].equals(value))
                {
                    return obj[0];
                }
            }
            return "";
        }

    }


    public static class CommonFormat
    {
        public static final String DATE_FORMAT = "dd-MM-yyyy";
        public static final String DATE_FORMAT_TIP = "DD-MM-YYYY";
        public static final String TIME_FORMAT_TIP = "hh:mm";
    }

    public static class SourceDetails
    {
        public static final String UNDEFINED_LABEL = "UN";
        public static final String UNDEFINED_VALUE = FacesHelper.getBundleMessage("source_select_source");

        public static final String EXISTING_CUSTOMER_LABEL = "EC";
        public static final String EXISTING_CUSTOMER_VALUE = FacesHelper.getBundleMessage("source_existing_customer");

        public static final String WEBSITE_TRIPTICOM_LABEL = "WT";
        public static final String WEBSITE_TRIPTICOM_VALUE = FacesHelper.getBundleMessage("source_website_tripticom");

        public static final String INTERNET_LABEL = "IT";
        public static final String INTERNET_VALUE = FacesHelper.getBundleMessage("source_internet");

        public static final String MAILING_LABEL = "ML";
        public static final String MAILING_VALUE = FacesHelper.getBundleMessage("source_mailing");

        public static final String ACQUISITION_BY_PHONE_LABEL = "AP";
        public static final String ACQUISITION_BY_PHONE_VALUE = FacesHelper.getBundleMessage("source_acquisition_by_phone");

        public static final String INDIRECTLY_LABEL = "IN";
        public static final String INDIRECTLY_VALUE = FacesHelper.getBundleMessage("source_indirectly");

        public static final String OTHER_LABEL = "OT";
        public static final String OTHER_VALUE = FacesHelper.getBundleMessage("source_other");
        public static final String[][] SOURCE={
                {UNDEFINED_LABEL, UNDEFINED_VALUE},
                {EXISTING_CUSTOMER_LABEL, EXISTING_CUSTOMER_VALUE},
                {WEBSITE_TRIPTICOM_LABEL, WEBSITE_TRIPTICOM_VALUE},
                {INTERNET_LABEL, INTERNET_VALUE},
                {MAILING_LABEL, MAILING_VALUE},
                {ACQUISITION_BY_PHONE_LABEL,ACQUISITION_BY_PHONE_VALUE},
                {INDIRECTLY_LABEL, INDIRECTLY_VALUE},
                {OTHER_LABEL, OTHER_VALUE}
        };
        public static List<SelectItem> getSourceItems()
        {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for(String[] str:SOURCE)
            {
                list.add(new SelectItem(str[0], str[1]));
            }
            return list;
        }
        public static String getSourceValue(String sourceLabel)
        {
            for(String[] str:SOURCE)
            {
               if(str[0].equals(sourceLabel))return str[1];
            }
          return "";
        }
        public static String getAffiliateValueByLabel(String label)
        {
            if (label.equals(EXISTING_CUSTOMER_LABEL))
            {
                return EXISTING_CUSTOMER_VALUE;
            }
            else if (label.equals(WEBSITE_TRIPTICOM_LABEL))
            {
                return WEBSITE_TRIPTICOM_VALUE;
            }
            else if (label.equals(INTERNET_LABEL))
            {
                return INTERNET_VALUE;
            }
            else if (label.equals(MAILING_LABEL))
            {
                return MAILING_VALUE;
            }
            else if (label.equals(ACQUISITION_BY_PHONE_LABEL))
            {
                return ACQUISITION_BY_PHONE_VALUE;
            }
            else if (label.equals(INDIRECTLY_LABEL))
            {
                return INDIRECTLY_VALUE;
            }
            else if (label.equals(OTHER_LABEL))
            {
                return OTHER_VALUE;
            }
            else if (label.equals(UNDEFINED_LABEL))
            {
                return UNDEFINED_VALUE;
            }
            else
            {
                return "";
            }
        }
    }

    public static class AffiliateDetails
    {
        public static final String TRIPTICOM_GROUP_LABEL = "T";
        public static final String TRIPTICOM_GROUP_VALUE = FacesHelper.getBundleMessage("affiliate_tripticom_group");
        public static final String IT84_LABEL = "8";
        public static final String IT84_VALUE = FacesHelper.getBundleMessage("affiliate_84it");
        public static final String WERVING_AND_SELECTIE_LABEL = "W";
        public static final String WERVING_AND_SELECTIE_VALUE = FacesHelper.getBundleMessage("affiliate_werving_selectie");
        public static final String UNDEFINED_LABEL = "U";
        public static final String UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");

        public static String getAffiliateValueByLabel(String label)
        {
            if (label.equals(TRIPTICOM_GROUP_LABEL))
            {
                return TRIPTICOM_GROUP_VALUE;
            }
            else if (label.equals(IT84_LABEL))
            {
                return IT84_VALUE;
            }
            else if (label.equals(WERVING_AND_SELECTIE_LABEL))
            {
                return WERVING_AND_SELECTIE_VALUE;
            }
            else if (label.equals(UNDEFINED_LABEL))
            {
                return UNDEFINED_VALUE;
            }
            else
            {
                return "";
            }
        }
    }

    public static class CountryDetails
    {
        public static String findCountryByCode(String code)
        {
            String country = "";
            if (code == null)
            {
                return country;
            }
            for (String lit[] : COUNTRY)
            {
                if (lit[0].equals(code.trim()))
                {
                    country = lit[1].trim();
                    return country;
                }

            }
            return country;
        }


        public static String findCodeByCountry(String country)
        {
            String code = UNDEFINED_LABEL;
            if (country == null)
            {
                return code;
            }
            for (String lit[] : COUNTRY)
            {
                if (lit[1].equals(country.trim()))
                {
                    code = lit[0].trim();
                    return code;
                }

            }
            return code;
        }

        public static final String NETHERLANDS_LABEL = "NL";
        public static final String NETHERLANDS_VALUE = FacesHelper.getBundleMessage("country_netherlands");
        public static final String GERMANY_LABEL = "DE";
        public static final String GERMANY_VALUE = FacesHelper.getBundleMessage("country_germany");
        public static final String BELGIUM_LABEL = "BE";
        public static final String BELGIUM_VALUE = FacesHelper.getBundleMessage("country_belgium");
        public static final String UKRAINE_LABEL = "UA";
        public static final String UKRAINE_VALUE = FacesHelper.getBundleMessage("country_ukraine");

        public static final String CHINA_LABEL = "CN";
        public static final String CHINA_VALUE = FacesHelper.getBundleMessage("country_china");

        public static final String MOROCCO_LABEL = "MA";
        public static final String MOROCCO_VALUE = FacesHelper.getBundleMessage("country_morocco");

        public static final String INDONESIA_LABEL = "ID";
        public static final String INDONESIA_VALUE = FacesHelper.getBundleMessage("country_indonesia");

        public static final String VIETNAM_LABEL = "VN";
        public static final String VIETNAM_VALUE = FacesHelper.getBundleMessage("country_vietnam");

        public static final String UNDEFINED_LABEL = "ZZ";
        public static final String UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");

        public static final String KINGDOM_VALUE = FacesHelper.getBundleMessage("country_kingdom");
        public static final String KINGDOM_LABEL = "GB";

        public static final String IRELAND_VALUE = FacesHelper.getBundleMessage("country_ireland");
        public static final String IRELAND_LABEL = "IE";


        public static final String COUNTRY[][] =
                {
                        {UNDEFINED_LABEL, UNDEFINED_VALUE},
                        {NETHERLANDS_LABEL, NETHERLANDS_VALUE},
                        {BELGIUM_LABEL, BELGIUM_VALUE},
                        {GERMANY_LABEL, GERMANY_VALUE},
                        {UKRAINE_LABEL, UKRAINE_VALUE},
                        {MOROCCO_LABEL, MOROCCO_VALUE},
                        {IRELAND_LABEL, IRELAND_VALUE},
                        {KINGDOM_LABEL, KINGDOM_VALUE}
                };


        public static String getCountryValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(IRELAND_LABEL))
            {
                return IRELAND_VALUE;
            }
            if (label.equals(KINGDOM_LABEL))
            {
                return KINGDOM_VALUE;
            }
            if (label.equals(NETHERLANDS_LABEL))
            {
                return NETHERLANDS_VALUE;
            }
            else if (label.equals(GERMANY_LABEL))
            {
                return GERMANY_VALUE;
            }
            else if (label.equals(BELGIUM_LABEL))
            {
                return BELGIUM_VALUE;
            }
            else if (label.equals(UKRAINE_LABEL))
            {
                return UKRAINE_VALUE;
            }
            else if (label.equals(CHINA_LABEL))
            {
                return CHINA_VALUE;
            }
            else if (label.equals(MOROCCO_LABEL))
            {
                return MOROCCO_VALUE;
            }
            else if (label.equals(INDONESIA_LABEL))
            {
                return INDONESIA_VALUE;
            }
            else if (label.equals(VIETNAM_LABEL))
            {
                return VIETNAM_VALUE;
            }
            else if (label.equals(UNDEFINED_LABEL))
            {
                return UNDEFINED_VALUE;
            }
            else
            {
                return "";
            }
        }
       public static List<SelectItem> countryList=getCountryList();
       public static List<SelectItem> getCountryList()
       {
           countryList = new ArrayList<SelectItem>();
           for (String lit[] : CountryDetails.COUNTRY)
           {
               countryList.add(new SelectItem(lit[0], lit[1]));

           }
         return  countryList;
       }
    }

    public static class RequestDetails
    {



        public static final String REQUEST_OPENED_STATUS_LABEL = "O";
        public static final String REQUEST_OPENED_STATUS_MESSAGE = "Opened";
        public static final String REQUEST_CLOSE_STATUS_LABEL = "C";
        public static final String REQUEST_CLOSE_STATUS_MESSAGE = "Closed";
        public static final String REQUEST_FINISH_STATUS_LABEL = "F";
        public static final String REQUEST_FINISH_STATUS_MESSAGE = "Finished";

        public static String getRequestStatusMessageByLabel(String label)
        {
            if (label.equals(REQUEST_OPENED_STATUS_LABEL))
            {
                return REQUEST_OPENED_STATUS_MESSAGE;
            }
            else if (label.equals(REQUEST_CLOSE_STATUS_LABEL))
            {
                return REQUEST_CLOSE_STATUS_MESSAGE;
            }
            else if (label.equals(REQUEST_FINISH_STATUS_LABEL))
            {
                return REQUEST_FINISH_STATUS_MESSAGE;
            }
            else
            {
                return "";
            }
        }


        public static final String CONTRACT_UNDEFINED_LABEL = "U";
        public static final String CONTRACT_UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");
        public static final String CONTRACT_PROJECT_LABEL = "P";
        public static final String CONTRACT_PROJECT_VALUE = FacesHelper.getBundleMessage("contract_project_term");
        //        public static final String CONTRACT_FIXED_TERM_LABEL = "B";
        //        public static final String CONTRACT_FIXED_TERM_VALUE = FacesHelper.getBundleMessage("contract_fixed_term");
        public static final String CONTRACT_FIXED_TERM_LABEL = "B";
        public static final String CONTRACT_FIXED_TERM_VALUE = FacesHelper.getBundleMessage("contract_temporary_term");
        public static final String CONTRACT_PERMANENT_LABEL = "V";
        public static final String CONTRACT_PERMANENT_VALUE = FacesHelper.getBundleMessage("contract_permanent_term");
        public static final String CONTRACT_FREELANCER_LABEL = "F";
        public static final String CONTRACT_FREELANCER_VALUE = FacesHelper.getBundleMessage("contract_freelancer_term");


        public static final String CONTRACT_TYPES[][] =
                {
                        {CONTRACT_UNDEFINED_LABEL, CONTRACT_UNDEFINED_VALUE},
                        {CONTRACT_PROJECT_LABEL, CONTRACT_PROJECT_VALUE},
                        {CONTRACT_PERMANENT_LABEL, CONTRACT_PERMANENT_VALUE},
                        {CONTRACT_FIXED_TERM_LABEL, CONTRACT_FIXED_TERM_VALUE},
                        {CONTRACT_FREELANCER_LABEL, CONTRACT_FREELANCER_VALUE},
                };

        public static String getContractTypeByCode(String code)
        {
            String country = null;
            for (String lit[] : CONTRACT_TYPES)
            {
                if (lit[0].equals(code.trim()))
                {
                    country = lit[1].trim();
                    return country;
                }

            }
            return country;
        }

        public static final String JOB_UNDEFINED_LABEL = "U";
        public static final String JOB_UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");
        public static final String JOB_FULLTIME_LABEL = "F";
        public static final String JOB_FULLTIME_VALUE = FacesHelper.getBundleMessage("job_fulltime");
        public static final String JOB_PARTTIME_LABEL = "P";
        public static final String JOB_PARTTIME_VALUE = FacesHelper.getBundleMessage("job_parttime");
        public static final String JOB_OP_LABEL = "O";
        public static final String JOB_OP_VALUE = FacesHelper.getBundleMessage("job_oncall");


        public static final String JOB_TYPES[][] =
                {
                        {JOB_UNDEFINED_LABEL, JOB_UNDEFINED_VALUE},
                        {JOB_FULLTIME_LABEL, JOB_FULLTIME_VALUE},
                        {JOB_PARTTIME_LABEL, JOB_PARTTIME_VALUE},
                        {JOB_OP_LABEL, JOB_OP_VALUE},
                };

        public static String getJobTypeByCode(String code)
        {
            String t = null;
            for (String lit[] : JOB_TYPES)
            {
                if (lit[0].equals(code.trim()))
                {
                    t = lit[1].trim();
                    return t;
                }

            }
            return t;
        }

        
        public static String getShiftTypeByCode(String code)
        {
            String t = null;
            for (String lit[] : SHIFT_TYPES)
            {
                if (lit[0].equals(code.trim()))
                {
                    t = lit[1].trim();
                    return t;
                }

            }
            return t;
        }


        public static String SHIFT_UNDEFINED_LABEL = "U";
        public static String SHIFT_UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");


        public static String SHIFT_DAY_LABEL = "D";
        public static String SHIFT_DAY_VALUE = FacesHelper.getBundleMessage("shift_day");

        public static String SHIFT_EVENING_LABEL = "A";
        public static String SHIFT_EVENING_VALUE = FacesHelper.getBundleMessage("evening_day");

        public static String SHIFT_VARIABLE_LABEL = "W";
        public static String SHIFT_VARIABLE_VALUE = FacesHelper.getBundleMessage("variable_day");

        public static final String[][] SHIFT_TYPES =
                {
                        {SHIFT_UNDEFINED_LABEL, SHIFT_UNDEFINED_VALUE},
                        {SHIFT_DAY_LABEL, SHIFT_DAY_VALUE},
                        {SHIFT_EVENING_LABEL, SHIFT_EVENING_VALUE},
                        {SHIFT_VARIABLE_LABEL, SHIFT_VARIABLE_VALUE},
                };

        public static String getCareerLevelByCode(String code)
        {
            String l = null;
            if(code==null)return "";
            for (String lit[] : CAREER_LEVELS)
            {
                if (lit[0].equals(code.trim()))
                {
                    l = lit[1].trim();
                    return l;
                }

            }
            return l;
        }

        public static String getEducationLevelByCode(String code)
        {
           if(code==null)return "";
            String l = null;
            for (String lit[] : EDUCATION_LEVELS)
            {
                if (lit[0].equals(code.trim()))
                {
                    l = lit[1].trim();
                    return l;
                }

            }
            return l;
        }

        public static final String SALARY_UNDEFINED_LABEL = "U";
        public static final String SALARY_UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");
        public static final String SALARY_PER_YEAR_LABEL = "J";
        public static final String SALARY_PER_YEAR_VALUE = FacesHelper.getBundleMessage("period_per_year");
        public static final String SALARY_PER_MONTH_LABEL = "M";
        public static final String SALARY_PER_MONTH_VALUE = FacesHelper.getBundleMessage("period_per_month");
        public static final String SALARY_PER_HOUR_LABEL = "H";
        public static final String SALARY_PER_HOUR_VALUE = FacesHelper.getBundleMessage("period_per_hour");
        public static final String SALARY_PIECE_LABEL = "P";
        public static final String SALARY_PIECE_VALUE = FacesHelper.getBundleMessage("period_piece");


        public static final String[][] SALARY_PERIODS =
                {
                        {SALARY_UNDEFINED_LABEL, SALARY_UNDEFINED_VALUE},
                        {SALARY_PER_YEAR_LABEL, SALARY_PER_YEAR_VALUE},
                        {SALARY_PER_MONTH_LABEL, SALARY_PER_MONTH_VALUE},
                        {SALARY_PER_HOUR_LABEL, SALARY_PER_HOUR_VALUE},
                        {SALARY_PIECE_LABEL, SALARY_PIECE_VALUE},
                };

        public static final String CAREER_LEVEL_UNDEFINED_LABEL = "U";
        public static final String CAREER_LEVEL_UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");

        public static final String[][] CAREER_LEVELS =
                {
                        {"U", "Undefined"},
                        {"S", "Start"},
                        {"C", "Midcareer"},
                        {"M", "Management"},
                        {"I", "Senior Management"},
                        {"D", "Directie"},
                };
        // Alex added LBO education Level bug 1198

        public static final String EDUCATION_LEVEL_UNDEFINED_LABEL = "U";
        public static final String EDUCATION_LEVEL_UNDEFINED_VALUE = FacesHelper.getBundleMessage("undefined");
        public static final String EDUCATION_LEVEL_LBO_LABEL = "L";
        public static final String EDUCATION_LEVEL_LBO_VALUE = "LBO";
        public static final String EDUCATION_LEVEL_MBO_LABEL = "M";
        public static final String EDUCATION_LEVEL_MBO_VALUE = "MBO";
        public static final String EDUCATION_LEVEL_HBO_LABEL = "H";
        public static final String EDUCATION_LEVEL_HBO_VALUE = "HBO";
        public static final String EDUCATION_LEVEL_WO_LABEL = "W";
        public static final String EDUCATION_LEVEL_WO_VALUE = "WO";
        public static final String EDUCATION_LEVEL_POST_DOCTOR_LABEL = "P";
        public static final String EDUCATION_LEVEL_POST_DOCTOR_VALUE = FacesHelper.getBundleMessage("education_post_doctor");


        public static final String[][] EDUCATION_LEVELS =
                {
                        {EDUCATION_LEVEL_LBO_LABEL, EDUCATION_LEVEL_LBO_VALUE},
                        {EDUCATION_LEVEL_MBO_LABEL, EDUCATION_LEVEL_MBO_VALUE},
                        {EDUCATION_LEVEL_HBO_LABEL, EDUCATION_LEVEL_HBO_VALUE},
                        {EDUCATION_LEVEL_WO_LABEL, EDUCATION_LEVEL_WO_VALUE},
                        {EDUCATION_LEVEL_POST_DOCTOR_LABEL, EDUCATION_LEVEL_POST_DOCTOR_VALUE},
                        {EDUCATION_LEVEL_UNDEFINED_LABEL, EDUCATION_LEVEL_UNDEFINED_VALUE},
                };

        public static String getSalaryPeriodByCode(String code)
        {
            String country = null;
            for (String lit[] : SALARY_PERIODS)
            {
                if (lit[0].equals(code.trim()))
                {
                    country = lit[1].trim();
                    return country;
                }

            }
            return country;
        }


        public static final String EXPERIENCE_UNDEFINED_MESSAGE = FacesHelper.getBundleMessage("undefined");
        public static final String EXPERIENCE_ABSENT_MESSAGE = FacesHelper.getBundleMessage("absent");
        public static final String EXPERIENCE_YEAR_MESSAGE = FacesHelper.getBundleMessage("year");
        public static final int EXPERIENCE_UNDEFINED_VALUE = -1;
        public static final int EXPERIENCE_ABSENT_VALUE = 0;
        public static final int EXPERIENCE_YEAR_VALUE = 1;
        public static final String EXPERIENCE_YEARS_MESSAGE = FacesHelper.getBundleMessage("years");
    }

    public static class RequestCandidateDetails
    {
        public static final String SELECTED_STATUS_VALUE = "Selected";
        public static final String SELECTED_STATUS_LABEL = "S";

        public static final String TO_SUGGEST_STATUS_VALUE = "To suggest";
        public static final String TO_SUGGEST_STATUS_LABEL = "T";

        public static final String SUGGESTED_STATUS_VALUE = "Suggested";
        public static final String SUGGESTED_STATUS_LABEL = "G";

        public static final String CV_REJECTED_STATUS_VALUE = "CV rejected";
        public static final String CV_REJECTED_STATUS_LABEL = "C";

        public static final String TO_INVITE_STATUS_VALUE = "To invite";
        public static final String TO_INVITE_STATUS_LABEL = "I";

        public static final String INVITED_STATUS_VALUE = "Invited";
        public static final String INVITED_STATUS_LABEL = "V";

        public static final String INVITED_AGAIN_STATUS_VALUE = "Invited again";
        public static final String INVITED_AGAIN_STATUS_LABEL = "D";

        public static final String REJECTED_STATUS_VALUE = "Rejected";
        public static final String REJECTED_STATUS_LABEL = "R";

        public static final String CAN_START_STATUS_VALUE = "Can start";
        public static final String CAN_START_STATUS_LABEL = "A";

        public static final String WILL_START_STATUS_VALUE = "Will start";
        public static final String WILL_START_STATUS_LABEL = "W";

        public static final String WILL_NOT_START_STATUS_VALUE = "Will not start";
        public static final String WILL_NOT_START_STATUS_LABEL = "N";

//        public static final String CAN_START_STATUS_VALUE = "Interview unsuccessful";
//        public static final String INTERVIEW_UNSUCCESSFUL_STATUS_LABEL = "V";

//        public static final String INTERVIEW_SUCCESSFUL_STATUS_VALUE = "Interview successful";
//        public static final String INTERVIEW_SUCCESSFUL_STATUS_LABEL = "W";

//        public static final String INTAKE_UNSUCCESSFUL_STATUS_VALUE = "Intake unsuccessful";
//        public static final String INTAKE_UNSUCCESSFUL_STATUS_LABEL = "R";

//        public static final String INTAKE_SUCCESSFUL_STATUS_VALUE = "Intake successful";
//        public static final String INTAKE_SUCCESSFUL_STATUS_LABEL = "T";

//        public static String getRequestCandidateMessageByLabel(String label)
//        {
//            if(label==null)return "";
//            if (label.equals(SUGGESTED_STATUS_LABEL))
//            {
//                return SUGGESTED_STATUS_VALUE;
//            }
//            if (label.equals(INTERVIEW_UNSUCCESSFUL_STATUS_LABEL))
//            {
//                return INTERVIEW_UNSUCCESSFUL_STATUS_VALUE;
//            }
//
//            if (label.equals(INTERVIEW_SUCCESSFUL_STATUS_LABEL))
//            {
//                return INTERVIEW_SUCCESSFUL_STATUS_VALUE;
//            }
//
//            if (label.equals(INTAKE_UNSUCCESSFUL_STATUS_LABEL))
//            {
//                return INTAKE_UNSUCCESSFUL_STATUS_VALUE;
//            }
//
//            if (label.equals(INTAKE_SUCCESSFUL_STATUS_LABEL))
//            {
//                return INTAKE_SUCCESSFUL_STATUS_VALUE;
//            }
//            if (label.equals(END_STATUS_LABEL))
//            {
//                return END_STATUS_VALUE;
//            }
//
//            return "";
//

        //        }

        public static String getRequestCandidateMessageByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(SELECTED_STATUS_LABEL))
            {
                return SELECTED_STATUS_VALUE;
            }
            if (label.equals(TO_SUGGEST_STATUS_LABEL))
            {
                return TO_SUGGEST_STATUS_VALUE;
            }
            if (label.equals(SUGGESTED_STATUS_LABEL))
            {
                return SUGGESTED_STATUS_VALUE;
            }
            if (label.equals(CV_REJECTED_STATUS_LABEL))
            {
                return CV_REJECTED_STATUS_VALUE;
            }

            if (label.equals(TO_INVITE_STATUS_LABEL))
            {
                return TO_INVITE_STATUS_VALUE;
            }

            if (label.equals(INVITED_STATUS_LABEL))
            {
                return INVITED_STATUS_VALUE;
            }

            if (label.equals(INVITED_AGAIN_STATUS_LABEL))
            {
                return INVITED_AGAIN_STATUS_VALUE;
            }

            if (label.equals(REJECTED_STATUS_LABEL))
            {
                return REJECTED_STATUS_VALUE;
            }
            if (label.equals(CAN_START_STATUS_LABEL))
            {
                return CAN_START_STATUS_VALUE;
            }
            if (label.equals(WILL_START_STATUS_LABEL))
            {
                return WILL_START_STATUS_VALUE;
            }
            if (label.equals(WILL_NOT_START_STATUS_LABEL))
            {
                return WILL_NOT_START_STATUS_VALUE;
            }

            return "";

        }
    }


    public static class ResourcesDetails
    {
        public static final String MAPPING_REQUEST_PUBLISH_PATH = "/templates/mapping.request.publish.xml";
        public static final String MAPPING_REQUEST_PUBLISHRESPONSE_PATH = "/templates/mapping.request.publishresponse.xml";

        public static final String MAPPING_RESUME_PUBLISH_PATH = "/templates/mapping.resume.publish.xml";
        public static final String MAPPING_RESUME_PUBLISHRESPONSE_PATH = "/templates/mapping.resume.publishresponse.xml";

        public static final String ZERO = "0";
        public static final String UNSUCCESSFUL = "-1";

        public static String PUBLISH_ENCODING = "UTF-8";
        public static final String IMAGE_PATH="/templates/logo84IT.jpg";    
    }

    public static class URLDetails
    {
        public static final String TRIPTICOM_SERVICE_PROPERTY_NAME = "tripticom.webservice.url";
        public static final String TRIPTICOM_SERVICE_RESUME_PROPERTY_NAME = "tripticom.webservice.resume.url";
        public static final String TRIPTICOM_SERVICE_URL = "http://tripticom.yellowmind.nl/parseVacXML.lasso";
        public static final String TRIPTICOM_RESUME_SERVICE_URL = "http://tripticom.yellowmind.nl/parseResumeXML.lasso";
        //cm84 post
        public static final String ADMINISTRATION_CM84_EMAIL = "admin84it@gmail.com";
        public static final String ADMINISTRATION_CM84_EMAIL_PASSWORD="administrator";
//.cm84config:
//tripticom.webservice.url = http://tripticom.yellowmind.nl/parseVacXML.lasso
//tripticom.webservice.resume.url = http://tripticom.yellowmind.nl/parseResumeXML.lasso

        
    }

    public static class InterviewAssessment
    {
        public static String UNDEFINED_LABEL = "U";
        public static String UNDEFINED_VALUE = FacesHelper.getBundleMessage("select_assessment");

        public static String POSITIVE_LABEL = "P";
        public static String POSITIVE_VALUE = FacesHelper.getBundleMessage("assessment_positive");

        public static String DOUBT_LABEL = "D";
        public static String DOUBT_VALUE = FacesHelper.getBundleMessage("assessment_doubt");

        public static String NEGATIVE_LABEL = "N";
        public static String NEGATIVE_VALUE = FacesHelper.getBundleMessage("assessment_negative");

        public static final String[][] TYPES =
                {
                        {UNDEFINED_LABEL, UNDEFINED_VALUE},
                        {POSITIVE_LABEL, POSITIVE_VALUE},
                        {DOUBT_LABEL, DOUBT_VALUE},
                        {NEGATIVE_LABEL, NEGATIVE_VALUE},
                };

        public static String getValueByCode(String code)
        {
            for (String lit[] : TYPES)
            {
                if (lit[0].equals(code.trim()))
                {
                    return lit[1];
                }
            }
            return null;
        }

        public static List<SelectItem> getItems()
        {
            List<SelectItem> list = new ArrayList<SelectItem>();
            list.add(new SelectItem(UNDEFINED_LABEL, UNDEFINED_VALUE));
            list.add(new SelectItem(POSITIVE_LABEL, POSITIVE_VALUE));
            list.add(new SelectItem(DOUBT_LABEL, DOUBT_VALUE));
            list.add(new SelectItem(NEGATIVE_LABEL, NEGATIVE_VALUE));
            return list;
        }


    }

    public static class EmploymentContractDetails
    {
        public static final String PERMANENT_TYPE_LABEL = "P";
        public static final String PERMANENT_TYPE_VALUE = "Permanent";
        public static final String TEMPORARY_TYPE_LABEL = "T";
        public static final String TEMPORARY_TYPE_VALUE = "Temporary";
        public static final String PROJECT_TYPE_LABEL = "J";
        public static final String PROJECT_TYPE_VALUE = "Project";
        public static String UNDEFINED_LABEL = "U";
        public static String UNDEFINED_VALUE = FacesHelper.getBundleMessage("select_value");

        public static String TYPE[][] = {
                {UNDEFINED_LABEL, UNDEFINED_VALUE},
                {PERMANENT_TYPE_LABEL, PERMANENT_TYPE_VALUE},
                {TEMPORARY_TYPE_LABEL, TEMPORARY_TYPE_VALUE},
                {PROJECT_TYPE_LABEL, PROJECT_TYPE_VALUE}
        };
        public static String NOTICE_PERIOD[][] = {
                {"0", FacesHelper.getBundleMessage("select_value")},
                {"W", FacesHelper.getBundleMessage("cal_month_d1")},
                {"M", FacesHelper.getBundleMessage("cal_month_d2")}
        };
        public static String getTypeValueByLabel(String label)
        {
            for (String[] obj : TYPE)
            {
                if (obj[0].equals(label))
                {
                    return obj[1];
                }
            }
            return "";
        }

        public static String getTypeLabelByValue(String value)
        {
            for (String[] obj : TYPE)
            {
                if (obj[1].equals(value))
                {
                    return obj[0];
                }
            }
            return "";
        }

        public static List<SelectItem> getNoticePeriodList()
        {
            List<SelectItem> list = new ArrayList<SelectItem>();
            for (String[] obj : NOTICE_PERIOD)
            {
                list.add(new SelectItem(obj[0], obj[1]));
            }
            return list;
        }

        public static String getNoticePeriodValueByLabel(String label)
        {
            for (String[] obj : NOTICE_PERIOD)
            {
                if (obj[0].equals(label))
                {
                    return obj[1];
                }
            }
            return "";
        }

        public static String getNoticePeriodLabelByValue(String value)
        {
            for (String[] obj : NOTICE_PERIOD)
            {
                if (obj[1].equals(value))
                {
                    return obj[0];
                }
            }
            return "";
        }
        // Statuses
        public static final String CREATED_LABEL = "C";
        public static final String CREATED_VALUE = "Created";
        public static final String CREATED_AFTER_ACCEPT_PERSON_LABEL = "X";
        public static final String CREATED_AFTER_ACCEPT_PERSON_VALUE = "Created after accepting person";
        public static final String CREATED_FROM_AA_LABEL = "W";
        public static final String CREATED_FROM_AA_VALUE = "Created from AssignmentAgreement";

        public static final String CREATED_MOTIVATION = FacesHelper.getBundleMessage("cr_aa_motiv");
        public static final String CHECK_PASSPORT_LABEL = "K";
        public static final String CHECK_PASSPORT_VALUE=FacesHelper.getBundleMessage("step_check_psp");
        public static final String PASSPORT_DETAILS_APPROVAL_LABEL="P";
        public static final String PASSPORT_DETAILS_APPROVAL_VALUE=FacesHelper.getBundleMessage("status_passport_det_app");
        public static final String CONTRACT_REGISTERED_LABEL = "Y";
        public static final String CONTRACT_REGISTERED_VALUE = "contract's registration";
        public static final String CONCEPT_APPROVAL_LABEL="A";
        public static final String CONCEPT_APPROVAL_VALUE=FacesHelper.getBundleMessage("step_concept_appr");
        public static final String FINAL_REJECTION_CONCEPT_LABEL="N";
        public static final String FINAL_REJECTION_CONCEPT_VALUE=FacesHelper.getBundleMessage("step_concept_fin_rej");
        public static final String APPROVED_LABEL="D";
        public static final String APPROVED_VALUE=FacesHelper.getBundleMessage("status_approved");
        public static final String PASSPORT_DETAILS_REGISTER_LABEL="R";
        public static final String PASSPORT_DETAILS_REGISTER_VALUE=FacesHelper.getBundleMessage("status_psp_reg");
        public static final String REJECT_CONCEPT_LABEL="L";
        public static final String REJECT_CONCEPT_VALUE=FacesHelper.getBundleMessage("status_reject_concept");
        public static final String CHANGING_CONCEPT_LABEL="G";
        public static final String CHANGING_CONCEPT_VALUE=FacesHelper.getBundleMessage("step_cntr_disagr");
        public static final String CHANGING_CONTRACT_LABEL="H";
        public static final String CHANGING_CONTRACT_VALUE=FacesHelper.getBundleMessage("step_change");
        public static final String CONTRACT_SIGNED_LABEL="S";
        public static final String CONTRACT_SIGNED_VALUE=FacesHelper.getBundleMessage("step_cntr_signed");
        public static final String FINAL_APPROVAL_LABEL="Q";
        public static final String FINAL_APPROVAL_VALUE=FacesHelper.getBundleMessage("step_fin_approval");
        public static final String FINAL_REJECTION_LABEL="J";
        public static final String FINAL_REJECTION_VALUE=FacesHelper.getBundleMessage("step_fin_rej");
        public static final String CHANGING_REPORT_LABEL="P";
        public static final String CHANGING_REPORT_VALUE=FacesHelper.getBundleMessage("edit_report");
        public static final String CANCEL_CONTRACT_LABEL="C";
        public static final String CANCEL_CONTRACT_VALUE=FacesHelper.getBundleMessage("edit_report");

        public static final String EC_FINISH_LABEL="F";
        public static final String EC_FINISH_VALUE=FacesHelper.getBundleMessage("status_end");
        public static final String EC_FINISH_SUCCESFULLY_LABEL="X";
        public static final String EC_FINISH_SUCCESFULLY_VALUE=FacesHelper.getBundleMessage("status_end_s");
        public static final String EC_FINISH_UNSUCCESFULLY_LABEL="Y";
        public static final String EC_FINISH_UNSUCCESFULLY_VALUE=FacesHelper.getBundleMessage("status_end_u");


        public static String getStatusValueByLabel(String label)
        {
            if (label == null)
            {
                return "";
            }
            if (label.equals(EC_FINISH_UNSUCCESFULLY_LABEL))
            {
                return EC_FINISH_UNSUCCESFULLY_VALUE;
            }
            if (label.equals(EC_FINISH_SUCCESFULLY_LABEL))
            {
                return EC_FINISH_SUCCESFULLY_VALUE;
            }
            if (label.equals(CREATED_FROM_AA_LABEL))
            {
                return CREATED_FROM_AA_VALUE;
            }
            if (label.equals(CHECK_PASSPORT_LABEL))
            {
                return CHECK_PASSPORT_VALUE;
            }
            if (label.equals(CONTRACT_REGISTERED_LABEL))
            {
                return CONTRACT_REGISTERED_VALUE;
            }

            if (label.equals(EC_FINISH_LABEL))
            {
                return EC_FINISH_VALUE;
            }
            if (label.equals(FINAL_REJECTION_LABEL))
            {
                return FINAL_REJECTION_VALUE;
            }
            if (label.equals(CONTRACT_SIGNED_LABEL))
            {
                return CONTRACT_SIGNED_VALUE;
            }
            if (label.equals(FINAL_APPROVAL_LABEL))
            {
                return FINAL_APPROVAL_VALUE;
            }
            if (label.equals(CHANGING_CONCEPT_LABEL))
            {
                return CHANGING_CONCEPT_VALUE;
            }
            if (label.equals(CHANGING_CONTRACT_LABEL))
            {
                return CHANGING_CONTRACT_VALUE;
            }
            if (label.equals(CREATED_AFTER_ACCEPT_PERSON_LABEL))
            {
                return CREATED_AFTER_ACCEPT_PERSON_VALUE;
            }
            if (label.equals(CREATED_LABEL))
            {
                return CREATED_VALUE;
            }
            if(label.equals(PASSPORT_DETAILS_APPROVAL_LABEL))
            {
                return PASSPORT_DETAILS_APPROVAL_VALUE;
            }
            if(label.equals(CONCEPT_APPROVAL_LABEL))
            {
                return CONCEPT_APPROVAL_VALUE;
            }
            if(label.equals(APPROVED_LABEL))
            {
                return APPROVED_VALUE;
            }
            if(label.equals(PASSPORT_DETAILS_REGISTER_LABEL))
            {
                return PASSPORT_DETAILS_REGISTER_VALUE;
            }

            return "";
        }

        public static String getStatusLabelByValue(String value)
        {
            if (value == null)
            {
                return "";
            }
            if (value.equals(CREATED_FROM_AA_VALUE))
            {
                return CREATED_FROM_AA_LABEL;
            }
            if (value.equals(CREATED_VALUE))
            {
                return CREATED_LABEL;
            }
            if(value.equals(PASSPORT_DETAILS_APPROVAL_VALUE))
            {
                return PASSPORT_DETAILS_APPROVAL_LABEL;
            }
            if(value.equals(CONCEPT_APPROVAL_VALUE))
            {
                return CONCEPT_APPROVAL_LABEL;
            }
            if(value.equals(APPROVED_VALUE))
            {
                return APPROVED_LABEL;
            }
            if(value.equals(PASSPORT_DETAILS_REGISTER_VALUE))
            {
                return PASSPORT_DETAILS_REGISTER_LABEL;
            }
            if (value.equals(CHANGING_CONCEPT_VALUE))
            {
                return CHANGING_CONCEPT_LABEL;
            }
            if (value.equals(CHANGING_CONTRACT_VALUE))
            {
                return CHANGING_CONTRACT_LABEL;
            }
            if (value.equals(FINAL_APPROVAL_VALUE))
            {
                return FINAL_APPROVAL_LABEL;
            }
            if (value.equals(CONTRACT_SIGNED_VALUE))
            {
                return CONTRACT_SIGNED_LABEL;
            }
            if (value.equals(FINAL_REJECTION_VALUE))
            {
                return FINAL_REJECTION_LABEL;
            }
            if (value.equals(EC_FINISH_VALUE))
            {
                return EC_FINISH_LABEL;
            }
            if (value.equals(EC_FINISH_UNSUCCESFULLY_VALUE))
            {
                return EC_FINISH_UNSUCCESFULLY_LABEL;
            }
            if (value.equals(EC_FINISH_SUCCESFULLY_VALUE))
            {
                return EC_FINISH_SUCCESFULLY_LABEL;
            }
            if (value.equals(CHECK_PASSPORT_VALUE))
            {
                return CHECK_PASSPORT_LABEL;
            }
            return "";
        }
        public static String CONCEPT = FacesHelper.getBundleMessage("concept");
    }
}
