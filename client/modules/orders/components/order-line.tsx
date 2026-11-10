"use client";

import { useTranslations } from "next-intl";
import { SelectField } from "@/shared/components/select-field";
import { TextField } from "@/shared/components/text-field";
import { Button } from "@/shared/components/ui/button";
import type { OrderLineValue } from "@/modules/orders/hooks/use-order-lines";

type Props = {
  line: OrderLineValue;
  index: number;
  options: { value: number; label: string }[];
  errors?: Record<string, string>;
  onChange: (patch: Partial<OrderLineValue>) => void;
  onRemove?: () => void;
};

export function OrderLine({ line, index, options, errors, onChange, onRemove }: Props) {
  const t = useTranslations("orders");

  return (
    <div className="grid grid-cols-[1fr_6rem_auto] items-start gap-2">
      <SelectField
        id={`productId-${line.key}`}
        name="productId"
        label={t("productLabel", { index: index + 1 })}
        placeholder={t("selectProduct")}
        options={options}
        value={line.productId}
        onChange={(event) => onChange({ productId: event.target.value })}
        error={errors?.[`items.${index}.productId`]}
      />
      <TextField
        id={`quantity-${line.key}`}
        name="quantity"
        label={t("fields.quantity")}
        inputMode="numeric"
        value={line.quantity}
        onChange={(event) => onChange({ quantity: event.target.value })}
        error={errors?.[`items.${index}.quantity`]}
      />
      <Button type="button" variant="ghost" className="mt-7" disabled={!onRemove} onClick={onRemove}>
        {t("removeLine")}
      </Button>
    </div>
  );
}
