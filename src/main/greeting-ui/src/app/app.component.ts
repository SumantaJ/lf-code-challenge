import { Component, OnInit } from '@angular/core';
import { Greeting } from './models/greeting';
import { GreetingService } from './services/greeting.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  greeting: any={};
  greetingForm: boolean;
  isNewGreeting: boolean;
  newGreeting: any = {};
  editGreetingForm: boolean;
  editGreeting: any = {};
  error: string;

  constructor(private greetingService: GreetingService ) { }

  ngOnInit() {
    this.get().subscribe(
      data => {
        this.greeting.id = data.id;
        this.greeting.message = data.message;
      },
      error => {
        this.error = error;
      }
    );
  }

  get() {
    return this.greetingService.getGreetingsFromData();
  }

  showEditForm(greeting: Greeting) {
    this.error = '';
    if (!this.greeting) {
      this.greetingForm = false;
      return;
    }
    this.editGreetingForm = true;
    this.editGreeting = greeting;
  }

  showForm() {
    this.error = '';
    // resets form if edited user
    if (this.greeting) {
      this.newGreeting = {};
    }
    this.greetingForm = true;
    this.isNewGreeting = true;
  }

  save(newGreeting: Greeting) {
    if (this.isNewGreeting) {
      // add a new user
      newGreeting.id = null;
      this.greetingService.addGreeting(newGreeting).subscribe(
        data => {
          this.greeting.id = data.id;
          this.greeting.message = data.message;
        },
        error => {
          this.error = error;
        }
      );
    }
    this.greetingForm = false;
  }

  update() {
    this.greetingService.updateGreeting(this.editGreeting).subscribe(
      data => {
        this.greeting.id = data.id;
        this.greeting.message = data.message;
      },
      error => {
        this.error = error;
      }
    );
    this.editGreetingForm = false;
    this.editGreeting = {};
  }

  cancelEdits() {
    this.editGreeting = {};
    this.editGreetingForm = false;
  }

  cancelSave() {
    this.newGreeting = {};
    this.greetingForm = false;
  }
  reloadDefault(){
    this.error = '';
    this.ngOnInit();
  }
}
