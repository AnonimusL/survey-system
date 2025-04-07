import {
    trigger,
    state,
    style,
    animate,
    transition
  } from '@angular/animations';
  
  export const fadeInOut = trigger('fadeInOut', [
    state('void', style({
      opacity: 0
    })),
    transition(':enter, :leave', [
      animate(300)
    ])
  ]);

  export const fadeIn = trigger('fadeIn', [
    state('void', style({ opacity: 0 })),
    transition(':enter, :leave', [
      animate(300, style({ opacity: 1 })),
    ]),
  ]);