import type { OrderStatus } from "@/modules/orders/types";

export const NEXT_STATUSES: Record<OrderStatus, OrderStatus[]> = {
  NEW: ["IN_PROGRESS", "CANCELLED"],
  IN_PROGRESS: ["COMPLETED", "CANCELLED"],
  COMPLETED: [],
  CANCELLED: [],
};

export const ORDER_STATUSES = Object.keys(NEXT_STATUSES) as OrderStatus[];
