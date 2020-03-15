import {Deserializable} from "./interface/deserializablel";

export class LoginPage implements Deserializable{
  username: string;
  password: string;
  errorMessage: string;
  hasError: boolean;
  successMessage: string;
  isSuccess : boolean;

  deserialize(input: any): this {
    Object.assign(this,input);
    return this;
  }
}
