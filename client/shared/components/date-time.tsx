import { useFormatter } from "next-intl";

export function DateTime({ value }: { value: string }) {
  const format = useFormatter();
  return <>{format.dateTime(new Date(value), { dateStyle: "short", timeStyle: "short" })}</>;
}
