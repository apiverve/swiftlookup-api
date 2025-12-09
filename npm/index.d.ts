declare module '@apiverve/swiftlookup' {
  export interface swiftlookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface swiftlookupResponse {
    status: string;
    error: string | null;
    data: SWIFTCodeLookupData;
    code?: number;
  }


  interface SWIFTCodeLookupData {
      count: number;
      banks: Bank[];
  }
  
  interface Bank {
      bank:        string;
      city:        string;
      branch:      string;
      swiftCode:   string;
      country:     string;
      countryCode: string;
  }

  export default class swiftlookupWrapper {
    constructor(options: swiftlookupOptions);

    execute(callback: (error: any, data: swiftlookupResponse | null) => void): Promise<swiftlookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: swiftlookupResponse | null) => void): Promise<swiftlookupResponse>;
    execute(query?: Record<string, any>): Promise<swiftlookupResponse>;
  }
}
