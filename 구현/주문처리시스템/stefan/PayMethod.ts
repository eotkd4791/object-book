export interface PayMethod {
  pay(amount: number, serial: string): void;
}
