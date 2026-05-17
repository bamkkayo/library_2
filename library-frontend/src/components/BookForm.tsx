'use client';

import { useState } from "react";
import { useRouter } from "next/navigation";

export default function BookForm() {
    const router = useRouter();
    const [formData, setFormData] = useState({ title: "", author: "", price: "" });
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!formData.title || !formData.author || !formData.price) return;

        setLoading(true);
        const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/books`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                title: formData.title,
                author: formData.author,
                price: Number(formData.price),
            }),
        });

        if (res.ok) {
            router.push("/");
            router.refresh();
        } else {
            alert("도서 등록에 실패했습니다.");
        }
        setLoading(false);
    };

    return (
        <form onSubmit={handleSubmit} className="bg-white border border-gray-200 rounded-xl p-6 shadow-sm space-y-4 max-w-lg mx-auto">
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-1">도서 제목</label>
                <input type="text" value={formData.title} onChange={(e) => setFormData({ ...formData, title: e.target.value })} className="w-full border border-gray-300 rounded-lg p-2.5 text-sm" placeholder="제목을 입력하세요" required />
            </div>
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-1">저자</label>
                <input type="text" value={formData.author} onChange={(e) => setFormData({ ...formData, author: e.target.value })} className="w-full border border-gray-300 rounded-lg p-2.5 text-sm" placeholder="저자를 입력하세요" required />
            </div>
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-1">가격</label>
                <input type="number" value={formData.price} onChange={(e) => setFormData({ ...formData, price: e.target.value })} className="w-full border border-gray-300 rounded-lg p-2.5 text-sm" placeholder="가격을 입력하세요" required />
            </div>
            <button type="submit" disabled={loading} className="w-full bg-blue-600 text-white font-medium py-2.5 rounded-lg hover:bg-blue-700 transition disabled:bg-gray-400">
                {loading ? "등록 중..." : "도서 등록 완료"}
            </button>
        </form>
    );
}