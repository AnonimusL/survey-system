import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterBySubject'
})
export class FilterBySubjectPipe implements PipeTransform {
  transform(surveys: any[], subject: string): any[] {
    if (!surveys || !subject) {
      return [];
    }
    return surveys.filter(survey => survey.surveySubject === subject);
  }
}
