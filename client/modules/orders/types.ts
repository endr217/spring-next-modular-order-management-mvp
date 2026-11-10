import type { Client } from "@/modules/clients/types";

export type OrderStatus = "NEW" | "IN_PROGRESS" | "COMPLETED" | "CANCELLED";

export type OrderItem = {
  productId: number;
  productName: string;
  quantity: number;
  unitPrice: number;
  subtotal: number;
};

export type Order = {
  id: number;
  client: Client;
  status: OrderStatus;
  items: OrderItem[];
  totalAmount: number;
  createdAt: string;
};

export type OrderSummary = Omit<Order, "client" | "items"> & { clientId: number; clientName: string };
