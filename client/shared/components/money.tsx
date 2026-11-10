import { useFormatter } from "next-intl";
import { currency } from "@/i18n/config";

export function Money({ value }: { value: number }) {
  const format = useFormatter();
  return <>{format.number(value, { style: "currency", currency })}</>;
}
