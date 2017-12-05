import { Injectable }          from '@angular/core';
import { Http }                from '@angular/http';
import { Observable }          from 'rxjs';
import { EventSourcePolyfill } from 'ng-event-source';

@Injectable()
export class ListService {
  constructor(private http:Http) {}

  private result = [];
  private subscribed = false;

  getResult() {
    return this.result;
  }

  subscribeToEvents(): void {
    if (this.subscribed) return;

    this.result = [];
    this.subscribed = true;

    var that = this;

    Observable.create((observer) => {
      let eventSource = this.createEventSource();

      eventSource.onmessage = (event) => {
        observer.next(JSON.parse(event.data));
      };

      eventSource.onerror = (event) => {
        event.target.close();
        this.subscribed = false;
      };
    }).subscribe({
      next: someValue => {
        that.result.push(someValue);
      },
    });
  }

  private createEventSource(): EventSourcePolyfill {
    return new EventSourcePolyfill("/reactive/person/list", {});
  }
}
