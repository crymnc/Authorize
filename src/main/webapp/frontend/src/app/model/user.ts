import {Role} from "./role";

export class User {

  id: number;
  name: string;
  lastName: string;
  username: string;
  active: boolean;
  lastActivationDate: any;
  roles: Array<Role> = new Array<Role>();

  constructor() {
  }

}
