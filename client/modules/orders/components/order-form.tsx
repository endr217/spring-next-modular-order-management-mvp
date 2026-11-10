"use client";

import { useTranslations } from "next-intl";
import { useActionState } from "react";
import { createOrder } from "@/modules/orders/actions/create-order";
import { FormActions } from "@/shared/components/form-actions";
import { FormMessage } from "@/shared/components/form-message";
import { ClientSelect } from "@/modules/orders/components/client-select";
import { OrderLine } from "@/modules/orders/components/order-line";
import { OrderTotal } from "@/modules/orders/components/order-total";
import { FieldError, FieldGroup } from "@/shared/components/ui/field";
import { useOrderLines } from "@/modules/orders/hooks/use-order-lines";
import { useProductOptions } from "@/modules/orders/hooks/use-product-options";
import { useSubmitWithoutReset } from "@/shared/forms/use-submit-without-reset";
import { EMPTY_FORM_STATE } from "@/shared/forms/form-state";
import type { Client } from "@/modules/clients/types";
import type { Product } from "@/modules/products/types";

export function OrderForm({ clients, products }: { clients: Client[]; products: Product[] }) {
  const [state, formAction, pending] = useActionState(createOrder, EMPTY_FORM_STATE);
  const { lines, add, remove, update, total } = useOrderLines(products);
  const options = useProductOptions(products);
  const onSubmit = useSubmitWithoutReset(formAction);
  const t = useTranslations("orders");

  return (
    <form onSubmit={onSubmit} noValidate>
      <FieldGroup>
        <FormMessage message={state.message} />
        <ClientSelect clients={clients} error={state.errors?.clientId} />
        {lines.map((line, index) => (
          <OrderLine
            key={line.key}
            line={line}
            index={index}
            options={options}
            errors={state.errors}
            onChange={(patch) => update(line.key, patch)}
            onRemove={lines.length > 1 ? () => remove(line.key) : undefined}
          />
        ))}
        <FieldError>{state.errors?.items}</FieldError>
        <OrderTotal total={total} onAdd={add} />
        <FormActions pending={pending} cancelHref="/orders" submitLabel={t("submit")} />
      </FieldGroup>
    </form>
  );
}
