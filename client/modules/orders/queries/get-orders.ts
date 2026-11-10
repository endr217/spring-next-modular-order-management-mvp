import "server-only";
import { apiFetch } from "@/shared/api/api-fetch";
import { toQuery } from "@/shared/api/to-query";
import type { OrderSummary } from "@/modules/orders/types";
import type { Page } from "@/shared/types";

type Query = { status?: string; page?: number; size?: number };

export function getOrders({ status, page = 0, size = 20 }: Query = {}) {
  return apiFetch<Page<OrderSummary>>(`/orders${toQuery({ status, page, size })}`);
}
