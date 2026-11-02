declare module '@apiverve/swiftlookup' {
  export interface swiftlookupOptions {
    api_key: string;
    secure?: boolean;
  }

  /**
   * Describes fields the current plan does not unlock. Locked fields arrive as null
   * in `data`; `locked_fields` names them, using dot paths for nested fields.
   * Absent when the plan unlocks everything.
   */
  export interface PremiumInfo {
    message: string;
    upgrade_url: string;
    locked_fields: string[];
  }

  export interface swiftlookupResponse {
    status: string;
    error: string | null;
    data: SWIFTCodeLookupData;
    code?: number;
    premium?: PremiumInfo;
  }


  interface SWIFTCodeLookupData {
      count: number | null;
      banks: Bank[];
  }
  
  interface Bank {
      bank:        null | string;
      city:        null | string;
      branch:      null | string;
      swiftCode:   null | string;
      country:     null | string;
      countryCode: null | string;
      isEu:        boolean | null;
      isEea:       boolean | null;
      isSepa:      boolean | null;
      bicDetails:  BicDetails;
  }
  
  interface BicDetails {
      bankCode:        null | string;
      countryCode:     null | string;
      locationCode:    null | string;
      branchCode:      null | string;
      bic8:            null | string;
      bic11:           null | string;
      isPrimaryOffice: boolean | null;
      isTestBic:       boolean | null;
  }

  export default class swiftlookupWrapper {
    constructor(options: swiftlookupOptions);

    execute(callback: (error: any, data: swiftlookupResponse | null) => void): Promise<swiftlookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: swiftlookupResponse | null) => void): Promise<swiftlookupResponse>;
    execute(query?: Record<string, any>): Promise<swiftlookupResponse>;
  }
}
