export const locales = ["en"] as const;
export const defaultLocale: Locale = "en";
export const localeCookie = "locale";
export const currency = "EUR";
export const timeZone = process.env.TIME_ZONE ?? "UTC";

export type Locale = (typeof locales)[number];
