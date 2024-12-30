export class ArrayList<T = any> extends Array<T> {
  constructor(...args: T[]) {
    super(...args);
  }

  public add(data: T) {
    this.push(data);
  }

  public remove(data: T) {
    const index = this.indexOf(data);
    if (index > -1) {
      this.splice(index, 1);
    }
  }

  public size() {
    return this.length;
  }

  public isEmpty() {
    return this.length === 0;
  }
}
