import { Component }   from '@angular/core';
import { Http }          from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { ListService } from './list.service';

@Component({
  selector: 'list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
  providers: [ ListService ],
})
export class ListComponent {
  errorMessage: string;
  result;

  nameValue = "";
  surnameValue = "";
  createResult = "";

  mode = 'Observable';

  constructor (private listService: ListService, private http: Http) {}

  get() {
    this.listService.subscribeToEvents();
    this.result = this.listService.getResult();
  }

  create() {
    if (!this.nameValue) { return; }
    if (!this.surnameValue) { return; }

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    var that = this;

    let res = this.http.post("/person/create", { "name": this.nameValue, "surname": this.surnameValue }, options).subscribe(
      result => {
        that.createResult = "Created."
      },
      error => {
        that.createResult = "Error occurred.";
      }
    );
  }
}
