export interface OrganizationDetailed {
    name: string;
    phoneNumber: string;
    webpage: string;
    logo: Uint8Array;
    active: boolean;
    addressDto: Address;
    organizationId: number;
    userId: number;
    about: string
  }

  export interface Address {
    country: string;
    city: string;
    postcode?: string;
    street?: string;
    number?: string;
  }

  export interface TargetUser {
    username: string;
    email: string;
    userGroup: string;
  }

  export interface LoginRequest{
    email: string,
    password: string,
  }
  