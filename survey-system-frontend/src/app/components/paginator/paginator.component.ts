import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent {
  @Input() length!: number;
  @Input() pageSize!: number;
  @Input() pageIndex!: number;
  
  @Output() pageChange = new EventEmitter<number>();

  constructor() { }

  nextPage() {
    this.pageChange.emit(this.pageIndex + 1);
  }

  previousPage() {
    this.pageChange.emit(this.pageIndex - 1);
  }

  getDisplayedPages(): number[] {
    const totalPages = Math.ceil(this.length / this.pageSize);
    const pagesToShow = 5;
    const middlePage = Math.floor(pagesToShow / 2);
    let start = Math.max(0, this.pageIndex - middlePage);
    let end = Math.min(totalPages - 1, start + pagesToShow - 1);

    if (end !== totalPages - 1 && end - start < pagesToShow - 1) {
      end = Math.min(start + pagesToShow - 1, totalPages - 1);
      start = Math.max(end - pagesToShow + 1, 0);
    }

    return Array.from({ length: end - start + 1 }, (_, i) => start + i);
  }

  goToPage(pageIndex: number) {
    if (pageIndex !== this.pageIndex) {
      this.pageChange.emit(pageIndex);
    }
  }

  goToFirstPage() {
    this.pageChange.emit(0);
  }

  goToLastPage() {
    const totalPages = Math.ceil(this.length / this.pageSize);
    this.pageChange.emit(totalPages - 1);
  }
}