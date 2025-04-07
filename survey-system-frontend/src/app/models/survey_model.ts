export interface Survey {
    id: number;
    title: string;
    description: string;
    logo: Uint8Array; 
    priv: boolean;
    activationDate: Date;
    deactivationDate: Date;
    active: boolean;
    organizationId: number;
    userId: number;
}

export interface SurveyDetailed extends Survey {
    questionForSurveyDto: QuestionForSurvey[];
}

export interface QuestionForSurvey {
    surveyId: number;
    number: number;
    text: string;
    type: string;
}

export interface QuestionForSurveyDet {
  surveyId: number;
  number: number;
  text: string;
  type: string;
  choiceDtoList: Choice[];
  scaleId: number;
  optionalField: ScaleValue[];
}

export  interface NodeWorkspace {
    id: number;
    title: string;
    parentId?: number | null;
    surveyId?: number | null;
    children?: NodeWorkspace[];
    active:boolean
    level: number
  }

  export  interface NodeWorkspaceActivation {
    surveyId?: number | null;
    children: number[];
    active:boolean
  }
  

  export interface OpenEndedForSurveyDto extends QuestionForSurvey {}
  
  export interface MultipleChoiceForSurveyDto extends QuestionForSurvey {
    choiceDtoList: Choice[];
  }
  
  export interface Choice{
    text: string;
    num: number;
  }
  
  export interface RatingScaleForSurveyDto extends QuestionForSurvey {
    scaleId: number;
    optionalField: ScaleValue[];
  }
  
  export interface ScaleValue {
    value: number;
    shortDescription: string;
  }
  
export interface UserSurvey {
    id: number;
    studentGroup: number;
    survey: string;
    surveyTitle: string;
    subject: string;
}

export interface SurveyInstance {
    id: number;
    connector: string;
    data: string;
    selected: boolean,
    activated: boolean,
    from: string,
    to: string,
}

export interface SurveysActivation {
    surveyName: string;
    activeFrom: string;
    activeTo: string;
    surveys: SurveyInstance[]
}
